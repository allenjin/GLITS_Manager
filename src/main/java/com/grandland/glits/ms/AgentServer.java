package com.grandland.glits.ms;

import com.grandland.glits.ms.protocol.HeartbeatService;
import com.grandland.glits.ms.protocol.MetricService;
import com.grandland.glits.ms.service.HeartbeatServiceImpl;
import com.grandland.glits.ms.service.MetricServiceImpl;
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

    private static final int METRIC_PORT = 9191;

    private TServer heartbeatServer;

    private TServer metricServer;

    @Autowired
    private HeartbeatServiceImpl heartbeatService;

    @Autowired
    private MetricServiceImpl metricService;

    public void start() {
        runHeartbeatServer();
        runMetricServer();
    }

    public void runHeartbeatServer() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HeartbeatService.Processor processor = new HeartbeatService.Processor(heartbeatService);
                    TServerTransport serverTransport = new TServerSocket(HEARTBEAT_PORT);
                    heartbeatServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
                    LOG.info("Agent heartbeat Server start...");

                    heartbeatServer.serve();
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    if (heartbeatServer != null) {
                        stopHeartbeatServer();
                    }
                }
            }
        }).start();
    }

    public void runMetricServer() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    MetricService.Processor processor = new MetricService.Processor(metricService);
                    TServerTransport serverTransport = new TServerSocket(METRIC_PORT);
                    metricServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
                    LOG.info("Agent metric Server start...");
                    metricServer.serve();
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    if (metricServer != null) {
                        stopMetricServer();
                    }
                }
            }
        }).start();
    }

    public void stopHeartbeatServer() {
        heartbeatServer.stop();
        heartbeatServer = null;
        LOG.info("Heartbeat Server stop");
    }

    public void stopMetricServer() {
        metricServer.stop();
        metricServer = null;
        LOG.info("Metric Server stop");
    }

}
