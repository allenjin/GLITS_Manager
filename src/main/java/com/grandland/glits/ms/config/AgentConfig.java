package com.grandland.glits.ms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * AgentConfig
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Configuration
public class AgentConfig {

    @Value("${agent.heartbeatInterval}")
    private long heartbeatInterval; //心跳间隔,单位[秒]

    @Value("${agent.cpuThreshold}")
    private double cpuThreshold;    //CPU报警瓶颈

    @Value("${agent.memThreshold}")
    private double memThreshold;    //内存报警瓶颈

    @Value("${agent.metricInterval}")
    private long metricInterval;    //采集间隔,单位[秒]

    public long getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public double getCpuThreshold() {
        return cpuThreshold;
    }

    public double getMemThreshold() {
        return memThreshold;
    }

    public long getMetricInterval() {
        return metricInterval;
    }
}
