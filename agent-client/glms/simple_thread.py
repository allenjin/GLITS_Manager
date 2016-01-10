import logging
import threading
import time

LOG = logging.getLogger(__name__)

class SimpleThread(threading.Thread):

    def __init__(self, name, fn, *args, **kwargs):
        threading.Thread.__init__(self, name=name)
        self.setDaemon(True)
        self._fn = fn
        self._args = args
        self._kwargs = kwargs
        self._should_stop = False
        self._timeout = None

    def run(self):
        while not self._should_stop:
            start = time.time()
            wait_time = self._timeout
            try:
                self._fn(*self._args, **self._kwargs)
            except Exception, _:
                LOG.exception("Exception in thread '%s'" % (self.getName(),))

            diff = time.time() - start
            if diff < wait_time:
                time.sleep(max(0, wait_time - diff))
        LOG.debug("Stopping %s" % (self.getName(),))

    def should_stop(self, latch=None):
        self._should_stop = True

    def set_name(self, val):
        self.setName(val)

    def set_timeout(self, timeout):
        self._timeout = timeout
