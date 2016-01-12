package com.grandland.glits.ms.monitor.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HostAlertCallback
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
@Component
public class HostAlertCallback implements AlertCallback {
    private static final Logger LOG = LoggerFactory.getLogger(HostAlertCallback.class);

    @Override
    public void notifyAlert() {

    }

    @Override
    public void cancelAlert(String hostName, AlertType type) {
        LOG.debug("Cancel Host Alert hostName={}, AlertType={}", hostName, type);
    }

    @Override
    public void handleAlert() {

    }
}
