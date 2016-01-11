package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.domain.GlHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * HostStatManager
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Service
public class HostStatManager {

    private ConcurrentMap hostStatMap;
    private ConcurrentMap glHostMap;


    private GlHostDAO glHostDAO;

    @Autowired
    public HostStatManager(GlHostDAO glHostDAO) {
        this.hostStatMap = new ConcurrentHashMap();
        this.glHostMap = new ConcurrentHashMap();
        this.glHostDAO = glHostDAO;
        init();
    }

    private void init() {
        for (GlHost host : glHostDAO.findByEnabled(true)) {
            addHost(host);
        }
    }

    public void addHost(GlHost host) {
        if (host == null) {
            return;
        }
        glHostMap.put(host.getHostName(), host);
    }

    public void removeHost(String hostName) {
        glHostMap.remove(hostName);
    }

    public GlHost getHost(String hostName) {
        return (GlHost) glHostMap.get(hostName);
    }

}
