package com.grandland.glits.ms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorConfig
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
@Configuration
@ConfigurationProperties(prefix = "monitor", locations = "classpath:config/monitor.yml")
public class MonitorConfig {

    private int cpuThreshold = 80;

    private int memThreshold = 85;

    private int diskThreshold = 5;

    public int getCpuThreshold() {
        return cpuThreshold;
    }

    public int getMemThreshold() {
        return memThreshold;
    }

    public int getDiskThreshold() {
        return diskThreshold;
    }
}
