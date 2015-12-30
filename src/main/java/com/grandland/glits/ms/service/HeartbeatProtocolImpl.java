package com.grandland.glits.ms.service;

import com.grandland.glits.ms.protocol.HeartbeatProtocol;
import com.grandland.glits.ms.protocol.HeartbeatRequest;
import com.grandland.glits.ms.protocol.HeartbeatResponse;
import org.apache.avro.AvroRemoteException;
import org.springframework.stereotype.Service;

/**
 * HeartbeatProtocolImpl
 *
 * @author Allen Jin
 * @date 2015/12/30
 */

@Service
public class HeartbeatProtocolImpl implements HeartbeatProtocol {

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest request) throws AvroRemoteException {
        return null;
    }
}
