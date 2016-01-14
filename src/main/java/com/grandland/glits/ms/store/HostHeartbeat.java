package com.grandland.glits.ms.store;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * HostHeartbeat
 *
 * @author Allen Jin
 * @date 2016/01/03
 */
public class HostHeartbeat {

    private int version;
    private String hostName;
    private Date lastSeen;
    private double cpuUsage;
    private double memUsage;
    private Map mountAvailSpace;
    private List<HostProcessStat> processStats;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(double memUsage) {
        this.memUsage = memUsage;
    }

    public Map getMountAvailSpace() {
        return mountAvailSpace;
    }

    public void setMountAvailSpace(Map mountAvailSpace) {
        this.mountAvailSpace = mountAvailSpace;
    }

    public List<HostProcessStat> getProcessStats() {
        return processStats;
    }

    public void setProcessStats(List<HostProcessStat> processStats) {
        this.processStats = processStats;
    }

    @Override
    public String toString() {
        return "HostHeartbeat{" +
                "version=" + version +
                ", hostName='" + hostName + '\'' +
                ", lastSeen=" + lastSeen +
                ", cpuUsage=" + cpuUsage +
                ", memUsage=" + memUsage +
                ", mountAvailSpace=" + mountAvailSpace +
                '}';
    }
}
