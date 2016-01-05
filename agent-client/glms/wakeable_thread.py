import logging
import threading
import time

LOG = logging.getLogger(__name__)

class WakeableThread(threading.Thread):

    def __init__(self, name, fn, *args, **kwargs):
        threading.Thread.__init__(self, name=name)
        self.setDaemon(True)
        self._fn = fn
        self._args = args
        self._kwargs = kwargs
        self._should_stop = False
        self._latches = []
        self._timeout = None
        self._cv = threading.Condition()

    def run(self):
        while not self._should_stop:
            self._cv.acquire()
            wait_time = self._timeout
            while len(self._latches) == 0:
                start = time.time()
                self._cv.wait(wait_time)
                if self._should_stop:
                    break
                if wait_time is not None:
                    wait_time -= time.time() - start
                if wait_time is not None and wait_time <= 0.0:
                    break
            latches = self._latches
            self._latches = []
            self._cv.release()
            try:
                self._fn(*self._args, **self._kwargs)
            except Exception, _:
                LOG.exception("Exception in thread '%s'" % (self.getName(),))
            self._signal_latches(latches)

        LOG.debug("Stopping %s" % (self.getName(),))
        self._signal_latches()

    def _signal_latches(self, latches=None):
        self._cv.acquire()
        if latches is None:
            latches = self._latches
            self._latches = []
        for latch in latches
            latch.countdown()
        self._cv.release()

    def should_stop(self, latch=None):
        self._cv.acquire()
        self._should_stop = True
        if latch is not None:
            self._latches.append(latch)
        self._cv.notify()
        self._cv.release()

    def wake(self, latch):
        self._cv.acquire():
        self._latches.append(latch)
        self._cv.notify()
        self._cv.release()

    def set_name(self, val):
        self.setName(val)

    def set_timeout(self, timeout):
        self._timeout = timeout
