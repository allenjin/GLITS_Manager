package com.grandland.glits.ms;

import com.grandland.glits.ms.protocol.HeartbeatProtocol;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * AgentServer
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Component
public class AgentServer {

    private static final Logger LOG = LoggerFactory.getLogger(AgentServer.class);

    private static Server heartbeatServer;

    @Autowired
    private HeartbeatProtocol heartbeatProtocol;

    @PostConstruct
    public void start() {
        heartbeatServer = new NettyServer(new SpecificResponder(HeartbeatProtocol.class, heartbeatProtocol), new InetSocketAddress(8888));
        heartbeatServer.start();
        LOG.info("Agent heartbeat Server start...");
    }

    @PreDestroy
    public void stop() {
        heartbeatServer.close();
        LOG.info("Agent heartbeat Server stop...");
    }

}
