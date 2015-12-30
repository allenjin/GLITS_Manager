package com.grandland.glits.ms;

import com.grandland.glits.ms.protocol.HeartbeatService;
import com.grandland.glits.ms.service.HeartbeatServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * AgentServer
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Component
public class AgentServer {

    private static final Logger LOG = LoggerFactory.getLogger(AgentServer.class);

    private static final int HEARTBEAT_PORT = 9090;

    private TServer server;

    @Autowired
    private HeartbeatServiceImpl heartbeatService;

    @PostConstruct
    public void start() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    HeartbeatService.Processor processor = new HeartbeatService.Processor(heartbeatService);
                    TServerTransport serverTransport = new TServerSocket(HEARTBEAT_PORT);
                    server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
                    LOG.info("Agent heartbeat Server start...");
                    server.serve();
                }catch (Exception e){
                    LOG.error(e.getMessage(),e);
                }
            }
        }).start();
    }

    @PreDestroy
    public void stop() {
        server.stop();
        LOG.info("Agent heartbeat Server stop...");
    }

}
