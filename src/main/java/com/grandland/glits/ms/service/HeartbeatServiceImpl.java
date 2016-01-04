package com.grandland.glits.ms.service;

import com.grandland.glits.ms.config.AgentConfig;
import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.protocol.HeartbeatRequest;
import com.grandland.glits.ms.protocol.HeartbeatResponse;
import com.grandland.glits.ms.protocol.HeartbeatService;
import com.grandland.glits.ms.store.HostHeartbeat;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * HeartbeatServiceImpl
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Service
public class HeartbeatServiceImpl implements HeartbeatService.Iface {

    private static final Logger LOG = LoggerFactory.getLogger(HeartbeatServiceImpl.class);

    @Autowired
    private GlHostDAO glHostDAO;

    @Autowired
    private AgentConfig agentConfig;

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest request) throws TException {
        LOG.debug("Heartbeat Server receive heartbeat = {}", request.toString());
        GlHost host = updateHostHeartbeat(request);
        return buildResponse(request);
    }

    public GlHost updateHostHeartbeat(HeartbeatRequest request) {
        GlHost host = glHostDAO.findByHostName(request.getHost_name());
        if (host == null) {
            host = glHostDAO.save(
                    new GlHost(request.getHost_name(),
                            request.getIp_address(),
                            new Date()));
            LOG.info("Can't find host in database, will create host[{}]", host);
        }
        HostHeartbeat hb = host.getHeartbeat();
        if (hb == null) {
            hb = new HostHeartbeat();
        }
        hb.setVersion(request.getVersion());
        hb.setCpuUsage(request.getTotal_cpu());
        hb.setHostName(request.getHost_name());
        hb.setLastSeen(new Date());
        hb.setMemUsage(request.getMemory_usage());
        hb.setMountAvailSpace(request.getMounted_avail_space());

        host.setHeartbeat(hb);

        return host;
    }

    public HeartbeatResponse buildResponse(HeartbeatRequest request) {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat_interval(agentConfig.getHeartbeatInterval());
        response.setHost_name(request.getHost_name());
        return response;
    }

}
