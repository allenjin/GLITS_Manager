package com.grandland.glits.ms.monitor.alert;

/**
 * AlertLevel
 *
 * @author Allen Jin
 * @date 2016/01/12
 */
public enum AlertLevel {

    DANGER("严重"), WARNING("警告");

    private String name;
    AlertLevel(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
