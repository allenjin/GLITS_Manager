#!/usr/bin/env python

import sys
import atexit
import time
import os
import signal
import psutil
import socket
import logging
import commands
import re
import logging.handlers

from protocol.heartbeat import HeartbeatService
from protocol.heartbeat.ttypes import HeartbeatRequest, HeartbeatResponse, Process, ProcessStatus
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from monitor import MonitorDaemon
from monitor import schema

LOG = logging.getLogger(__name__)

DEFAULT_HEARTBEAT_INTERVAL_SECONDS = 5.0
DEFAULT_AGENT_TIMEOUT = 30.0
AGENT_SERVER_HOST = "localhost"
AGENT_SERVER_PORT = 9090
METRIC_SERVER_PORT = 9191

AGENT_LOG_MODE = "DEBUG"
LOG_FORMAT = "%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s"
LOG_DATE_FORMAT = "%a, %d %b %Y %H:%M:%S"
LOG_FILE_NAME = "glits-ms-agent.log"

class Agent(object):

    def __init__(self):
        initial_logging()
        self.identifier = "agent-%d-%d" % (os.getpid(), long(time.time()))
        self.heartbeat_retry_factor = 60
        self.heartbeat_retry_times = 0
        self.agent_version = 1
        self.heartbeat_interval = DEFAULT_HEARTBEAT_INTERVAL_SECONDS
        self.agent_timeout = DEFAULT_AGENT_TIMEOUT
        self.started = False
        self.hostname = socket.gethostname()
        self.ip_address = socket.gethostbyname(self.hostname)
        self.monitor_daemon = None
        self.process_stats = []

    def configure(self):
        pid = os.getpid()
        if pid == os.getpgid(pid):
            LOG.info("Not starting a new session.")
        else:
            os.setsid()
        atexit.register(self.stop)
        self.should_exit = False
        self.signal_caught = None
        signal.signal(signal.SIGINT, self.ready_exit_on_signal)
        signal.signal(signal.SIGTERM, self.ready_exit_on_signal)

    def prepare_heartbeat_request(self):
        cpu_usage = psutil.cpu_percent(interval=None)
        phymem = psutil.virtual_memory()
        memory_usage = phymem.percent
        #auto add agent process info to process_stats, the role id is 0 and role name is 'Agent' by default
        self.process_stats.append(self.get_agent_status())
        processes = self.process_stats
        mounted_avail_space = dict()
        for partition in psutil.disk_partitions():
            mountpoint = partition.mountpoint
            mounted_avail_space[mountpoint] = psutil.disk_usage(mountpoint).free
        return HeartbeatRequest(self.agent_version,
                                self.hostname,
                                self.ip_address,
                                cpu_usage,
                                memory_usage,
                                processes,
                                mounted_avail_space)

    def handle_heartbeat_response(self, response):
        self.heartbeat_interval = response.heartbeat_interval
        if self.agent_timeout is not response.metric_interval:
            self.agent_timeout = response.metric_interval
            self.monitor_daemon.set_timeout(self.agent_timeout)
        self.handle_host_process(response.processes)

    def handle_host_process(self, processList):
        self.process_stats = []
        for process in processList:
            pid = self.getPidByType(process.name, process.type)
            if pid is None:
                LOG.error("can't find process use name={%s}, will ignore", process.name)
                continue
            else:
                try:
                    sys_ps = psutil.Process(pid)
                    ps_stats = self.processStatsBuild(process.id, process.name, sys_ps)
                    self.process_stats.append(ps_stats)
                except:
                    LOG.error("can't find process use pid={%s}, will ignore", pid)
                    continue

    def processStatsBuild(self, r_id, r_name, sys_ps):
        ps_stats = dict()
        ps_stats[schema.PS_CPU_USAGE] = sys_ps.cpu_percent(interval=None)
        ps_stats[schema.PS_MEM_USAGE] = sys_ps.memory_percent()
        ps_stats[schema.PS_CPU_TIME] = sys_ps.cpu_times().user
        ps_stats[schema.PS_CREATE_TIME] = sys_ps.create_time()
        ps_stats[schema.PS_NUM_THREADS] = sys_ps.num_threads()
        processStatus = ProcessStatus(r_id, r_name, sys_ps.status(), sys_ps.pid, sys_ps.username(), ps_stats)
        return processStatus

    def getPidByType(self, name, p_type):
        pid = ''
        if p_type == 'JAVA':
            pid = commands.getoutput('jps | grep ' + name + " | awk 'NR==1 {print $1}'")
        elif p_type == 'SYS':
            pid = commands.getoutput('ps -elf | grep ' + name + " | awk 'NR==1 {print $2}'")
        pid = (None if pid == '' else int(pid))
        return pid

    def get_agent_status(self):
        agent_stats = dict()
        pid = os.getpid()
        agent_ps = psutil.Process(pid)
        return self.processStatsBuild(0, "Agent", agent_ps)

    def send_heartbeat(self, request):
        transport = TSocket.TSocket(AGENT_SERVER_HOST, AGENT_SERVER_PORT)
        #t_http.setTimeout(DEFAULT_AGENT_TIMEOUT)
        transport = TTransport.TBufferedTransport(transport)
        protocol = TBinaryProtocol.TBinaryProtocol(transport)
        client = HeartbeatService.Client(protocol)
        transport.open()

        response = client.heartbeat(request)
        transport.close()

        return response

    def start(self):
        self.started = True

        self.monitor_daemon = MonitorDaemon(self.hostname, AGENT_SERVER_HOST, METRIC_SERVER_PORT, self)
        self.monitor_daemon.set_timeout(self.agent_timeout)
        self.monitor_daemon.start()

        #main loop for sending heartbeat
        should_heartbeat = True
        while not self.should_exit:
            last = time.time()
            try:
                request = self.prepare_heartbeat_request()
                LOG.debug(request)
                response = self.send_heartbeat(request)
                #if send heartbeat success, reset retry time to zero
                self.heartbeat_retry_times = 0
                self.handle_heartbeat_response(response)
            except Thrift.TException, tx:
                LOG.exception("Caught unexpected exception {%s} in main loop while sending heartbeat", tx.message)
                retry_sleep_time = self.heartbeat_retry_times * self.heartbeat_retry_factor
                LOG.exception("will retry to send heartbeat, sleep time={%s}", retry_sleep_time)
                self.heartbeat_retry_times += 1
                time.sleep(retry_sleep_time)

            diff = time.time() - last
            if diff < self.heartbeat_interval:
                time.sleep(max(0, self.heartbeat_interval - diff))

        self.stop()
        LOG.info("Agent exiting; caught signal %s" % (self.signal_caught))
        sys.exit(0)

    def ready_exit_on_signal(self, sig_num, stack_frame):
        self.signal_caught = sig_num
        self.should_exit = True

    def stop(self):
        if not self.started:
            return
        # do whatever before stopping agent
        self.started = False

def get_logging_level():
    if AGENT_LOG_MODE == 'DEBUG':
        return logging.DEBUG
    else:
        return logging.INFO

def initial_logging():
    logging.basicConfig(level=get_logging_level(),
        format=LOG_FORMAT, datefmt=LOG_DATE_FORMAT,filename=LOG_FILE_NAME)
    # output logs to console
    console = logging.StreamHandler()
    console.setLevel(get_logging_level())
    formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
    console.setFormatter(formatter)
    logging.getLogger('').addHandler(console)

def main():
    agent = Agent()
    agent.configure()
    agent.start()

if __name__ == "__main__":
    main()
