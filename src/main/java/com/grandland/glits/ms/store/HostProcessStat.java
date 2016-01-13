package com.grandland.glits.ms.store;

import java.util.Map;

/**
 * HostProcessStat
 *
 * @author Allen Jin
 * @date 2016/01/13
 */
public class HostProcessStat {
    //role id
    private int id;
    //进程号
    private int pid;
    //进程名称
    private String name;
    //进程状态
    private String status;
    //用户名
    private String userName;

    //进程状态信息
    private Map<Integer,Double> stats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Integer, Double> getStats() {
        return stats;
    }

    public void setStats(Map<Integer, Double> stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "HostProcessStat{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                ", stats=" + stats +
                '}';
    }
}
