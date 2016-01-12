package com.grandland.glits.ms.monitor;

import com.grandland.glits.ms.monitor.alert.AlertCallback;
import com.grandland.glits.ms.store.HostHeartbeat;

/**
 * BaseInspector
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
public abstract class BaseInspector {

    protected AlertCallback alertCallback;

    public BaseInspector(AlertCallback alertCallback){
        this.alertCallback = alertCallback;
    }

    /**
     * According to given heartbeat, generate a report
     * @param heartbeat
     * @return
     */
    protected abstract InspectorResult report(HostHeartbeat heartbeat);

    public void inspect(HostHeartbeat heartbeat){
        InspectorResult result = report(heartbeat);

    }

}
