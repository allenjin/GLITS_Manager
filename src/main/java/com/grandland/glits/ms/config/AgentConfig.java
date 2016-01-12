package com.grandland.glits.ms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * AgentConfig
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Configuration
@ConfigurationProperties(prefix = "agent", locations = "classpath:config/monitor.yml")
public class AgentConfig {

    private int heartbeatInterval = 30; //心跳间隔,单位[秒]

    private int metricInterval = 60;    //采集间隔,单位[秒]

    private int heartbeatServerPort = 9090; //心跳监听端口

    private int metricServerPort = 9191;    //采集监听端口

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public int getMetricInterval() {
        return metricInterval;
    }

    public int getHeartbeatServerPort() {
        return heartbeatServerPort;
    }

    public int getMetricServerPort() {
        return metricServerPort;
    }
}
