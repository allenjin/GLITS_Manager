package com.grandland.glits.ms.monitor;

import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.monitor.alert.AlertMessage;

import java.util.Date;
import java.util.List;

/**
 * InspectorResult
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
public class InspectorResult {

    private GlHost.HostStatus status;

    private List<AlertMessage> alertMsgs;

    private Date time;


    public List<AlertMessage> getAlertMsgs() {
        return alertMsgs;
    }

    public void setAlertMsgs(List<AlertMessage> alertMsgs) {
        this.alertMsgs = alertMsgs;
    }
}
