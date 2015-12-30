package com.grandland.glits.ms.service;

import com.grandland.glits.ms.protocol.HeartbeatRequest;
import com.grandland.glits.ms.protocol.HeartbeatResponse;
import com.grandland.glits.ms.protocol.HeartbeatService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * HeartbeatServiceImpl
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Service
public class HeartbeatServiceImpl implements HeartbeatService.Iface{

    private static final Logger LOG = LoggerFactory.getLogger(HeartbeatServiceImpl.class);

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest request) throws TException {
        LOG.info(request.toString());
        LOG.debug("debug");
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat_interval(30);
        response.setHost_name(request.getHost_name());
        return response;
    }
}
