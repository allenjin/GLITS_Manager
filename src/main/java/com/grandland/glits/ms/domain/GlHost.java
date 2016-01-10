package com.grandland.glits.ms.domain;

import com.grandland.glits.ms.store.HeartbeatStore;
import com.grandland.glits.ms.store.HostHeartbeat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * GlHost
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Entity
@Table(name = "gl_hosts")
public class GlHost {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "host_name", nullable = false, unique = true)
    private String hostName;

    @Column(name = "ip_address")
    private String ipAddress;   //IP地址

    private boolean enabled = true;    //可用

    @Column(name = "last_update")
    private Date lastUpdate;    //最近更新时间

    @Enumerated(value = EnumType.STRING)
    private HostStatus status = HostStatus.NORMAL;  //机器状态

    @ManyToOne
    @JoinColumn(name = "rack_id", referencedColumnName = "id")
    private GlRack rack;    //所属机架

    @ManyToMany(mappedBy = "hosts", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<GlRole> roles;

    public GlHost() {
    }

    public GlHost(String hostName, String ipAddress, Date lastUpdate) {
        this.hostName = hostName;
        this.ipAddress = ipAddress;
        this.lastUpdate = lastUpdate;
    }

    public enum HostStatus {
        NORMAL,     //正常[绿]
        OFFLINE,    //离线[灰]
        WARNING,    //警告[黄]
        RISKY      //危险[红]
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public HostStatus getStatus() {
        return status;
    }

    public void setStatus(HostStatus status) {
        this.status = status;
    }

    public GlRack getRack() {
        return rack;
    }

    public void setRack(GlRack rack) {
        this.rack = rack;
    }

    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public HostHeartbeat getHeartbeat() {
        return HeartbeatStore.getInstance().getHostHeatbeat(this.hostName);
    }

    public void setHeartbeat(HostHeartbeat heartbeat) {
        HeartbeatStore.getInstance().setHostHeartbeat(this, heartbeat);
    }

    @Override
    public String toString() {
        return "GlHost{" +
                "id=" + id +
                ", hostName='" + hostName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", enabled=" + enabled +
                ", lastUpdate=" + lastUpdate +
                ", status=" + status +
                ", rack=" + rack +
                ", roles=" + roles +
                '}';
    }
}
