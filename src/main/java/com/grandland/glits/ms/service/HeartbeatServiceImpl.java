package com.grandland.glits.ms.service;

import com.grandland.glits.ms.config.AgentConfig;
import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.domain.GlRole;
import com.grandland.glits.ms.protocol.*;
import com.grandland.glits.ms.protocol.Process;
import com.grandland.glits.ms.store.HostHeartbeat;
import com.grandland.glits.ms.store.HostProcessStat;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    private HostStatManager hostStatManager;

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest request) throws TException {
        LOG.debug("Heartbeat Server receive heartbeat = {}", request.toString());
        GlHost host = updateHostHeartbeat(request);

        return buildResponse(host, request);
    }

    private GlHost updateHostHeartbeat(HeartbeatRequest request) {
        GlHost host = glHostDAO.findByHostName(request.getHost_name());
        if (host == null) {
            host = glHostDAO.save(
                    new GlHost(request.getHost_name(),
                            request.getIp_address(),
                            new Date()));
            hostStatManager.addHost(host);
            LOG.info("Can't find host in database, will create host[{}]", host);
        }
        HostHeartbeat hb = host.getHeartbeat();
        if (hb == null) {
            hb = new HostHeartbeat();
        }
        hb.setVersion(request.getVersion());
        hb.setCpuUsage(request.getCpu_usage());
        hb.setHostName(request.getHost_name());
        hb.setLastSeen(new Date());
        hb.setMemUsage(request.getMem_usage());
        hb.setMountAvailSpace(request.getMounted_avail_space());
        List<HostProcessStat> processStats = handleProcessStatus(request.getProcesses_stats());
        hb.setProcessStats(processStats);
        host.setHeartbeat(hb);

        return host;
    }

    private List handleProcessStatus(List<ProcessStatus> processStats) {
        List<HostProcessStat> hostPsList = new LinkedList<>();
        for (ProcessStatus pss : processStats) {
            HostProcessStat hps = new HostProcessStat();
            hps.setId(pss.getId());
            hps.setName(pss.getName());
            hps.setPid(pss.getPid());
            hps.setStatus(pss.getStatus());
            hps.setUserName(pss.getUsername());
            hps.setStats(pss.getStats());
            hostPsList.add(hps);
        }
        return hostPsList;
    }


    private HeartbeatResponse buildResponse(GlHost host, HeartbeatRequest request) {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setHeartbeat_interval(agentConfig.getHeartbeatInterval());
        response.setMetric_interval(agentConfig.getMetricInterval());
        response.setHost_name(request.getHost_name());
        response.setProcesses(buildProcess(host));
        return response;
    }

    private List<Process> buildProcess(GlHost host) {
        List processList = new LinkedList();
        if (host.getRoles() != null) {
            for (GlRole role : host.getRoles()) {
                Process process = new Process();
                process.setId(role.getId());
                process.setName(role.getName());
                process.setRunning(role.isRunning());
                process.setAuto_restart(role.isAutoRestart());
                process.setScript(role.getScript());
                process.setType(role.getCategory().toString());
                processList.add(process);
            }
        }

        return processList;
    }

}
