package com.grandland.glits.ms.service;

import com.grandland.glits.ms.metric.common.MetricKey;
import com.grandland.glits.ms.metric.common.MonitoringType;
import com.grandland.glits.ms.metric.persist.MetricInfo;
import com.grandland.glits.ms.metric.persist.MetricInfoDAO;
import com.grandland.glits.ms.metric.utils.MetricUtil;
import com.grandland.glits.ms.protocol.FsUpdate;
import com.grandland.glits.ms.protocol.MetricMessage;
import com.grandland.glits.ms.protocol.MetricService;
import com.grandland.glits.ms.protocol.NetUpdate;
import org.apache.thrift.TException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * MetricServiceImpl
 *
 * @author Allen Jin
 * @date 2016/01/04
 */

@Service
public class MetricServiceImpl implements MetricService.Iface {
    private static final Logger LOG = LoggerFactory.getLogger(MetricServiceImpl.class);


    @Autowired
    private MetricInfoDAO metricInfoDAO;

    @Override
    public void sendMetricMessage(List<MetricMessage> messages) throws TException {

        LOG.debug(messages.toString());

        List metricList = new LinkedList();
        for (MetricMessage message : messages) {
            String hostName = message.getHost_name();
            Date updateTime = new Date(message.getTs_secs() * 1000L);
            Map hostUpdates = MetricUtil.extracMetricValues(message.getMetrics());
            metricList.add(parseToBasicInfo(hostUpdates, hostName, updateTime));
            metricList.add(parseToCpuInfo(hostUpdates, hostName, updateTime));
            metricList.add(parseToMemInfo(hostUpdates, hostName, updateTime));
            List netUpdaets = message.getNet_updates();
            metricList.add(parseToNetInfo(netUpdaets, hostName, updateTime));
            List fsUpdates = message.getFs_updates();
            metricList.add(parseToFsInfo(fsUpdates, hostName, updateTime));
        }
        LOG.debug(metricList.toString());
        metricInfoDAO.save(metricList);
    }

    //机器基本信息
    private MetricInfo parseToBasicInfo(Map map, String hostName, Date date){
        JSONObject node = new JSONObject();
        node.put("load_avg_1", map.get(MetricKey.LOAD_1));
        node.put("load_avg_5", map.get(MetricKey.LOAD_5));
        node.put("load_avg_15", map.get(MetricKey.LOAD_15));
        node.put("os", map.get(MetricKey.OS));
        node.put("uptime", map.get(MetricKey.UP_TIME));
        node.put("sever_date",map.get(MetricKey.SYSTEM_DATE));

        return new MetricInfo(hostName, MonitoringType.HOST_BASIC_INFO, node.toString(), date);
    }

    //机器CPU使用率
    private MetricInfo parseToCpuInfo(Map map, String hostName, Date date){
        JSONObject node = new JSONObject();
        node.put("usage", map.get(MetricKey.CPU_PERCENT));

        return new MetricInfo(hostName, MonitoringType.HOST_CPU, node.toString(), date);
    }

    //机器内存信息
    private MetricInfo parseToMemInfo(Map map, String hostName, Date date){
        JSONObject node = new JSONObject();
        node.put("total", map.get(MetricKey.PHY_MEM_TOTAL));
        node.put("used", map.get(MetricKey.PHY_MEM_USED));
        node.put("free", map.get(MetricKey.PHY_MEM_FREE));
        node.put("buffers", map.get(MetricKey.PHY_MEM_BUFFERS));
        node.put("cached", map.get(MetricKey.PHY_MEM_CACHED));

        return new MetricInfo(hostName, MonitoringType.HOST_MEM, node.toString(), date);
    }

    //机器网络信息
    private MetricInfo parseToNetInfo(List<NetUpdate> netUpdates, String hostName, Date date){
        JSONArray array = new JSONArray();
        for(NetUpdate update : netUpdates){
            Map map = MetricUtil.extracMetricValues(update.getMetrics());
            JSONObject node = new JSONObject();
            node.put("iface", update.getIface());
            node.put("ip", map.get(MetricKey.IP_ADDRESS));
            node.put("bytes_sent", map.get(MetricKey.BYTES_SENT));
            node.put("bytes_recv", map.get(MetricKey.BYTES_RECV));
            node.put("packets_sent", map.get(MetricKey.PACKETS_SENT));
            node.put("packets_recv", map.get(MetricKey.PACKETS_RECV));
            node.put("err_in", map.get(MetricKey.ERR_IN));
            node.put("err_out", map.get(MetricKey.ERR_OUT));
            node.put("drop_in", map.get(MetricKey.DROP_IN));
            node.put("drop_out", map.get(MetricKey.DROP_OUT));
            array.put(node);
        }
        return new MetricInfo(hostName, MonitoringType.HOST_NETWORK, array.toString(), date);
    }

    //机器磁盘信息
    private MetricInfo parseToFsInfo(List<FsUpdate> fsUpdates, String hostName, Date date){
        JSONArray array = new JSONArray();
        for(FsUpdate update : fsUpdates){
            Map map = MetricUtil.extracMetricValues(update.getMetrics());
            JSONObject node = new JSONObject();
            node.put("partition", update.getPartition());
            node.put("mount_point", update.getMount_point());
            node.put("total", map.get(MetricKey.CAPACITY));
            node.put("used", map.get(MetricKey.CAPACITY_USED));
            node.put("free", map.get(MetricKey.CAPACITY_FREE));
            array.put(node);
        }
        return new MetricInfo(hostName, MonitoringType.HOST_FILE_SYSTEM, array.toString(), date);
    }
}
