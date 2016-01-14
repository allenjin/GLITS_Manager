package com.grandland.glits.ms.metric.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.json.HostProcessRecord;
import com.grandland.glits.ms.json.MetricInfoRecord;
import com.grandland.glits.ms.metric.common.MetricGrading;
import com.grandland.glits.ms.metric.common.MetricKey;
import com.grandland.glits.ms.metric.common.MonitoringType;
import com.grandland.glits.ms.metric.persist.MetricInfo;
import com.grandland.glits.ms.metric.persist.MetricInfoDAO;
import com.grandland.glits.ms.store.HostHeartbeat;
import com.grandland.glits.ms.store.HostProcessStat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * MetricInfoService
 *
 * @author Allen Jin
 * @date 2016/01/10
 */

@Service
public class MetricInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(MetricInfoService.class);
    @Autowired
    private MetricInfoDAO metricInfoDAO;

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<MetricInfoRecord> query(String hostName, MonitoringType type, int size) {
        PageRequest request = new PageRequest(0, size);
        Page<MetricInfo> page = metricInfoDAO.findByHostNameAndTypeAndGradingOrderByIdDesc(hostName, type, MetricGrading.MINUTE, request);
        List records = new LinkedList();
        List<MetricInfo> metricInfoList = page.getContent();
        for (int i = metricInfoList.size() - 1; i >= 0; i--) {
            MetricInfo metricInfo = metricInfoList.get(i);
            try {
                records.add(new MetricInfoRecord(hostName,
                        metricInfo.getUpdateTime(),
                        objectMapper.readTree(metricInfo.getData())
                ));
            } catch (IOException ex) {
                LOG.error("objectMapper readTree error:{}", ex.getMessage());
            }
        }
        return records;
    }

    public String MetricInfoJson(String hostName, MonitoringType type, int size) {
        String json = "";

        List<MetricInfoRecord> metricInfos = query(hostName, type, size);
        try {
            json = objectMapper.writeValueAsString(metricInfos);
        } catch (JsonProcessingException e) {
            LOG.error("metric info convert to json error:{}", e.getMessage());
        }
        return json;
    }

    public String hostProcessStatsJson(GlHost host) {
        String json = "";
        List<HostProcessRecord> list = new LinkedList<>();
        HostHeartbeat hb = host.getHeartbeat();
        if (hb != null) {
            List<HostProcessStat> hpsList = hb.getProcessStats();
            for (HostProcessStat hps : hpsList) {
                HostProcessRecord record = new HostProcessRecord();
                record.setId(hps.getId());
                record.setName(hps.getName());
                record.setStatus(hps.getStatus());
                record.setPid(hps.getPid());
                record.setUsername(hps.getUserName());
                if(hps.getStats() != null){
                    record.setCpuTime(hps.getStats().get(MetricKey.PS_CPU_TIME));
                    record.setCpuUsage(hps.getStats().get(MetricKey.PS_CPU_USAGE));
                    record.setCreatTime(hps.getStats().get(MetricKey.PS_CREATE_TIME));
                    record.setMemUsage(hps.getStats().get(MetricKey.PS_MEM_USAGE));
                    record.setNumThreads(hps.getStats().get(MetricKey.PS_NUM_THREADS));
                }
                list.add(record);
            }
        }
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            LOG.error("host process stats info convert to json error:{}", e.getMessage());
        }
        return json;
    }


}
