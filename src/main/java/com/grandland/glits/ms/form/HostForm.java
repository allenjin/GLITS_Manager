package com.grandland.glits.ms.form;

import java.util.List;

/**
 * HostForm
 *
 * @author Allen Jin
 * @date 2016/02/16
 */
public class HostForm {

    private boolean updated = false;
    private String hostName;
    private String ipAddress;
    private int rackId;
    private List<Integer> roleIds;

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "HostForm{" +
                "updated=" + updated +
                ", hostName='" + hostName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", rackId=" + rackId +
                ", roleIds=" + roleIds +
                '}';
    }
}
