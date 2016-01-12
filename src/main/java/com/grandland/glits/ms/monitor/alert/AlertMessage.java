package com.grandland.glits.ms.monitor.alert;

import java.util.Date;

/**
 * AlertMessage
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
public class AlertMessage {

    private AlertType type;
    private AlertLevel level;
    private String info;
    private Date time;

    public AlertMessage(AlertType type, AlertLevel level, String info, Date time) {
        this.level = level;
        this.type = type;
        this.info = info;
        this.time = time;
    }

    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public AlertLevel getLevel() {
        return level;
    }

    public void setLevel(AlertLevel level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
