
import logging
import os
import platform
import threading
import time
import psutil

from simple_thread import SimpleThread
from monitor import schema
from monitor import MTYPES
from protocol.metric import MetricService
from protocol.metric.ttypes import MetricValue, NetUpdate, FsUpdate, MetricMessage
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

LOG = logging.getLogger("monitor")

class MonitorDaemon(object):

    def __init__(self, hostname, host, port, agent=None):
        self._agent = agent
        self._hostname = hostname
        self._reporting_thread = SimpleThread("MonitorDaemon-Reporter", self._report)
        self._metric_host = host
        self._metric_port = port
        self._message_fail_cache = []
        self._message_cache_max_nums = 50

    def _report(self):
        messages = self._prepare_messages()
        try:
            self._send_message(messages)
            # if send message success, we clear cached messages
            self._message_fail_cache = []
        except Thrift.TException, tx:
            LOG.exception("Caught unexpected exception {%s} in main loop while sending metric messages.", tx.message)

    def _send_message(self, messages):
        transport = TSocket.TSocket(self._metric_host, self._metric_port)
        transport = TTransport.TBufferedTransport(transport)
        protocol = TBinaryProtocol.TBinaryProtocol(transport)
        client = MetricService.Client(protocol)
        transport.open()
        client.sendMetricMessage(messages)
        transport.close()

    # we need to send cached fail message to server
    def _prepare_messages(self):
        message = self._message_builder()
        LOG.debug(message)
        if len(self._message_fail_cache) == self._message_cache_max_nums:
            del self._message_fail_cache[0]
        self._message_fail_cache.append(message)
        return self._message_fail_cache

    def _message_builder(self):
        metrics = self._collect_base_metrics()
        net_updates = self._collect_net_updates()
        fs_updates = self._collect_fs_updates()
        return MetricMessage(time.time(), self._hostname, None, metrics, net_updates, fs_updates)

    def _collect_base_metrics(self):
        metrics = []
        self._add_metric(metrics, schema.OS, platform.platform(), MTYPES.TYPE_STRING)

        uptime = time.time() - psutil.boot_time()
        self._add_metric(metrics, schema.UP_TIME, uptime, MTYPES.TYPE_DOUBLE)

        # local_time = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
        self._add_metric(metrics, schema.SYSTEM_DATE, time.time(), MTYPES.TYPE_LONG)

        self._add_metric(metrics, schema.CPU_PERCENT, psutil.cpu_percent(interval=None), MTYPES.TYPE_DOUBLE)

        load_avg = os.getloadavg()
        self._add_metric(metrics, schema.LOAD_1, load_avg[0], MTYPES.TYPE_DOUBLE)
        self._add_metric(metrics, schema.LOAD_5, load_avg[1], MTYPES.TYPE_DOUBLE)
        self._add_metric(metrics, schema.LOAD_15, load_avg[2], MTYPES.TYPE_DOUBLE)

        mem_usage = psutil.virtual_memory()
        self._add_metric(metrics, schema.PHY_MEM_USED, mem_usage.used, MTYPES.TYPE_LONG)
        #self._add_metric(metrics, schema.PHY_MEM_CACHED, mem_usage.cached, MTYPES.TYPE_LONG)
        #self._add_metric(metrics, schema.PHY_MEM_BUFFERS, mem_usage.buffers, MTYPES.TYPE_LONG)
        self._add_metric(metrics, schema.PHY_MEM_TOTAL, mem_usage.total, MTYPES.TYPE_LONG)
        self._add_metric(metrics, schema.PHY_MEM_FREE, mem_usage.available, MTYPES.TYPE_LONG)

        swap_usage = psutil.swap_memory()
        self._add_metric(metrics, schema.SWAP_MEM_TOTAL, swap_usage.total, MTYPES.TYPE_LONG)
        self._add_metric(metrics, schema.SWAP_MEM_FREE, swap_usage.free, MTYPES.TYPE_LONG)
        self._add_metric(metrics, schema.SWAP_MEM_USED, swap_usage.used, MTYPES.TYPE_LONG)

        return metrics

    def _collect_net_updates(self):
        net_updates = []
        net_infos = psutil.net_io_counters(pernic=True)
        net_addrs = psutil.net_if_addrs()
        for iface_name in net_addrs:

            iface = net_infos[iface_name]
            address = net_addrs[iface_name][0].address
            metrics = []
            self._add_metric(metrics, schema.BYTES_SENT, iface.bytes_sent, MTYPES.TYPE_LONG)
            self._add_metric(metrics, schema.BYTES_RECV, iface.bytes_recv, MTYPES.TYPE_LONG)
            # self._add_metric(metrics, schema.PACKETS_SENT, iface.bytes_sent, MTYPES.TYPE_LONG)
            # self._add_metric(metrics, schema.PACKETS_RECV, iface.bytes_sent, MTYPES.TYPE_LONG)
            # self._add_metric(metrics, schema.ERR_IN, iface.errin, MTYPES.TYPE_LONG)
            # self._add_metric(metrics, schema.ERR_OUT, iface.errout, MTYPES.TYPE_LONG)
            # self._add_metric(metrics, schema.DROP_IN, iface.dropin, MTYPES.TYPE_LONG)
            # self._add_metric(metrics, schema.DROP_OUT, iface.dropout, MTYPES.TYPE_LONG)
            self._add_metric(metrics, schema.IP_ADDRESS, address, MTYPES.TYPE_LONG)
            net_updates.append(NetUpdate(iface_name, metrics))

        return net_updates

    def _collect_fs_updates(self):
        fs_updates = []
        disk_partitions = psutil.disk_partitions()
        for partition in disk_partitions:
            metrics = []
            disk_usage = psutil.disk_usage(partition.mountpoint)
            self._add_metric(metrics, schema.CAPACITY, disk_usage.total, MTYPES.TYPE_LONG)
            self._add_metric(metrics, schema.CAPACITY_USED, disk_usage.used, MTYPES.TYPE_LONG)
            self._add_metric(metrics, schema.CAPACITY_FREE, disk_usage.free, MTYPES.TYPE_LONG)
            fs_updates.append(FsUpdate(partition.mountpoint, partition.device, metrics))
        return fs_updates

    def _add_metric(self, list, key, val, type):
        list.append(MetricValue(key, str(val), type))

    def set_timeout(self, timeout):
        self._reporting_thread.set_timeout(timeout)

    def start(self):
        self._reporting_thread.start()
