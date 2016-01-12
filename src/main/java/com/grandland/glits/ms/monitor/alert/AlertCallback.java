package com.grandland.glits.ms.monitor.alert;

/**
 * AlertCallback
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
public interface AlertCallback {

    void notifyAlert();

    void cancelAlert(String hostName, AlertType type);

    void handleAlert();
}
