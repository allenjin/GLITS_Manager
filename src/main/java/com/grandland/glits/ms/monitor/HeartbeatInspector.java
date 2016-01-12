package com.grandland.glits.ms.monitor;

import com.grandland.glits.ms.config.MonitorConfig;
import com.grandland.glits.ms.monitor.alert.*;
import com.grandland.glits.ms.store.HostHeartbeat;
import com.grandland.glits.ms.utils.HostUnitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * HeartbeatInspector
 *
 * @author Allen Jin
 * @date 2016/01/12
 */

@Component
public class HeartbeatInspector extends BaseInspector {

    @Autowired
    private MonitorConfig monitorConfig;

    @Autowired
    public HeartbeatInspector(HostAlertCallback alertCallback) {
        super(alertCallback);
    }

    @Override
    protected InspectorResult report(HostHeartbeat heartbeat) {
        String hostName = heartbeat.getHostName();
        InspectorResult result = new InspectorResult();
        List alertMsgList = new LinkedList();
        if (heartbeat.getCpuUsage() > monitorConfig.getCpuThreshold()) {
            String info = "CPU使用率超过%" + heartbeat.getCpuUsage();
            alertMsgList.add(new AlertMessage(AlertType.HOST_CPU, AlertLevel.WARNING, info, new Date()));
        } else {
            alertCallback.cancelAlert(hostName, AlertType.HOST_CPU);
        }
        if (heartbeat.getMemUsage() > monitorConfig.getMemThreshold()) {
            String info = "内存使用率超过%" + heartbeat.getMemUsage();
            alertMsgList.add(new AlertMessage(AlertType.HOST_MEM, AlertLevel.WARNING, info, new Date()));
        } else {
            alertCallback.cancelAlert(hostName, AlertType.HOST_MEM);
        }
        long rootAvailBytes = (Long) heartbeat.getMountAvailSpace().get("/");
        double rootSpace = HostUnitUtil.byteToGB(rootAvailBytes);
        if (rootSpace < monitorConfig.getDiskThreshold()) {
            String info = "系统根目录剩余空间偏低,剩余空间" + rootSpace + "GB";
            alertMsgList.add(new AlertMessage(AlertType.HOST_DISK, AlertLevel.WARNING, info, new Date()));
        } else {
            alertCallback.cancelAlert(hostName, AlertType.HOST_DISK);
        }

        result.setAlertMsgs(alertMsgList);

        return result;
    }

}
