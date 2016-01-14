package com.grandland.glits.ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.glits.ms.config.SiteConfig;
import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.metric.common.MonitoringType;
import com.grandland.glits.ms.metric.service.MetricInfoService;
import com.grandland.glits.ms.service.RackService;
import com.grandland.glits.ms.store.HostHeartbeat;
import com.grandland.glits.ms.store.HostProcessStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * MetricController
 *
 * @author Allen Jin
 * @date 2016/01/07
 */

@Controller
@RequestMapping("/physical")
public class PhysicalController {

    private static final Logger LOG = LoggerFactory.getLogger(PhysicalController.class);

    @Autowired
    private MetricInfoService metricInfoService;

    @Autowired
    private RackService rackService;

    @Autowired
    private GlHostDAO glHostDAO;

    @Autowired
    private SiteConfig siteConfig;

    private static final int METRIC_SIZE = 10;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("site", siteConfig);
        model.put("racks", rackService.queryRacks());
        return "physical/racks";
    }

    @RequestMapping("/host/{hostId}")
    public String hostPhysicalView(@PathVariable("hostId") int hostId, Map<String, Object> model) {
        GlHost host = glHostDAO.findOne(hostId);
        model.put("host", host);
        model.put("basicInfo", metricInfoService.MetricInfoJson(host.getHostName(), MonitoringType.HOST_BASIC_INFO,1));
        model.put("cpuInfo", metricInfoService.MetricInfoJson(host.getHostName(), MonitoringType.HOST_CPU, METRIC_SIZE));
        model.put("memInfo", metricInfoService.MetricInfoJson(host.getHostName(), MonitoringType.HOST_MEM, METRIC_SIZE));
        model.put("netInfo", metricInfoService.MetricInfoJson(host.getHostName(), MonitoringType.HOST_NETWORK, METRIC_SIZE));
        model.put("fsInfo", metricInfoService.MetricInfoJson(host.getHostName(), MonitoringType.HOST_FILE_SYSTEM, METRIC_SIZE));
        model.put("psInfo", metricInfoService.hostProcessStatsJson(host));
        return "physical/host";
    }

}
