package com.grandland.glits.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Application
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        AgentServer agentServer = context.getBean(AgentServer.class);
        agentServer.start();
    }

}
