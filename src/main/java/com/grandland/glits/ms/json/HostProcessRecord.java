package com.grandland.glits.ms.json;

import java.util.Date;

/**
 * HostProcessRecord
 *
 * @author Allen Jin
 * @date 2016/01/14
 */
public class HostProcessRecord {
    private int id;
    private String name;
    private int pid;
    private String status;
    private String username;
    private double cpuUsage;
    private double memUsage;
    private double creatTime;
    private double numThreads;
    private double cpuTime;
    private double runTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public double getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(double creatTime) {
        this.runTime = new Date().getTime() - creatTime * 1000;
        this.creatTime = creatTime;
    }

    public double getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(double numThreads) {
        this.numThreads = numThreads;
    }

    public double getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(double cpuTime) {
        this.cpuTime = cpuTime;
    }

    public double getRunTime() {
        return this.runTime;
    }

    @Override
    public String toString() {
        return "HostProcessRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", memUsage=" + memUsage +
                ", creatTime=" + creatTime +
                ", numThreads=" + numThreads +
                ", cpuTime=" + cpuTime +
                '}';
    }
}
