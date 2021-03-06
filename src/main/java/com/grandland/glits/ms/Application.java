package com.grandland.glits.ms;

import com.grandland.glits.ms.config.AgentConfig;
import com.grandland.glits.ms.config.MonitorConfig;
import com.grandland.glits.ms.config.SiteConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

/**
 * Application
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@SpringBootApplication
@EnableConfigurationProperties({SiteConfig.class, AgentConfig.class, MonitorConfig.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        AgentServer agentServer = context.getBean(AgentServer.class);
//        agentServer.start();
    }

}
