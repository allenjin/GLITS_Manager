package com.grandland.glits.ms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SiteConfig
 *
 * @author Allen Jin
 * @date 2016/01/12
 */

@Configuration
@ConfigurationProperties(prefix = "site", locations = "classpath:config/site.yml")
public class SiteConfig {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
