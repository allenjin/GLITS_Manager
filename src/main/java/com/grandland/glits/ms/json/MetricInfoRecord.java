package com.grandland.glits.ms.json;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;

/**
 * MetricInfoRecord
 *
 * @author Allen Jin
 * @date 2016/01/10
 */
public class MetricInfoRecord {

    private String host;
    private Date time;
    private JsonNode data;

    public MetricInfoRecord(){}

    public MetricInfoRecord(String host, Date time, JsonNode data) {
        this.host = host;
        this.time = time;
        this.data = data;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MetricInfoRecord{" +
                "host='" + host + '\'' +
                ", time=" + time +
                ", data=" + data +
                '}';
    }
}
