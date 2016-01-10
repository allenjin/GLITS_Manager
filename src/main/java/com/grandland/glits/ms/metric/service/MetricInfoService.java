package com.grandland.glits.ms.metric.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.glits.ms.json.MetricInfoRecord;
import com.grandland.glits.ms.metric.common.MetricGrading;
import com.grandland.glits.ms.metric.common.MonitoringType;
import com.grandland.glits.ms.metric.persist.MetricInfo;
import com.grandland.glits.ms.metric.persist.MetricInfoDAO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private List<MetricInfoRecord> query(String hostName, MonitoringType type, int size) {
        PageRequest request = new PageRequest(0, size);
        Page<MetricInfo> page = metricInfoDAO.findByHostNameAndTypeAndGrading(hostName, type, MetricGrading.MINUTE, request);
        List records = new LinkedList();
        for (MetricInfo metricInfo : page.getContent()) {
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

}
