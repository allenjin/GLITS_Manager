package com.grandland.glits.ms.metric.persist;

import com.grandland.glits.ms.metric.common.MetricGrading;
import com.grandland.glits.ms.metric.common.MonitoringType;

import javax.persistence.*;
import java.util.Date;

/**
 * MetricInfo
 *
 * @author Allen Jin
 * @date 2016/01/08
 */

@Entity
@Table(name = "agent_metric_info", indexes = {
        @Index(name = "IDX_HOST_NAME", columnList = "host_name")
})
public class MetricInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "host_name", nullable = false)
    private String hostName;

    @Column(nullable = false)
    private MonitoringType type;

    private MetricGrading grading = MetricGrading.MINUTE;

    @Lob
    private String data;

    @Column(name = "update_time")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public MetricInfo(){}

    public MetricInfo(String hostName, MonitoringType type, String data, Date updateTime){
        this.hostName = hostName;
        this.type =type;
        this.data = data;
        this.updateTime = updateTime;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public MonitoringType getType() {
        return type;
    }

    public void setType(MonitoringType type) {
        this.type = type;
    }

    public MetricGrading getGrading() {
        return grading;
    }

    public void setGrading(MetricGrading grading) {
        this.grading = grading;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MetricInfo{" +
                "id=" + id +
                ", hostName='" + hostName + '\'' +
                ", type=" + type +
                ", grading=" + grading +
                ", data='" + data + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
