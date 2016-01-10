package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.metric.service.MetricInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private MetricInfoService metricInfoService;

    @RequestMapping("/{hostName}")
    public String hostPhysicalView(@PathVariable("hostName") String hostName, Map<String, Object> model){
        model.put("hostname", hostName);
        return "physical/host";
    }
}
