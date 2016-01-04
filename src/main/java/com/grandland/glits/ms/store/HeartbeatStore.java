package com.grandland.glits.ms.store;

import com.grandland.glits.ms.domain.GlHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * HeartbeatStore
 * 用于缓存机器的心跳信息
 *
 * @author Allen Jin
 * @date 2016/01/03
 */

public class HeartbeatStore {
    private static final Logger LOG = LoggerFactory.getLogger(HeartbeatStore.class);

    private static final HeartbeatStore $THIS = new HeartbeatStore();
    private ConcurrentMap hostHeartbeatMap;

    private HeartbeatStore() {
        this.hostHeartbeatMap = new ConcurrentHashMap();
    }

    public static HeartbeatStore getInstance() {
        return $THIS;
    }

    public HostHeartbeat getHostHeatbeat(String hostName) {
        if (hostName == null) {
            return null;
        } else {
            return (HostHeartbeat) hostHeartbeatMap.get(hostName);
        }
    }

    public void setHostHeartbeat(GlHost host, HostHeartbeat heartbeat) {
        if (heartbeat == null) {
            hostHeartbeatMap.remove(host.getHostName());
        } else {
            hostHeartbeatMap.put(host.getHostName(), heartbeat);
        }
    }

}
