package com.grandland.glits.ms.metric.common;

/**
 * MetricGrading
 *
 * @author Allen Jin
 * @date 2016/01/10
 */
public enum MetricGrading {
    MINUTE(1),
    MINUTE_10(10),
    MINUTE_30(30),
    HOUR(60),
    HOUR_6(6*60),
    DAY(24*60);

    private int minutes;
    MetricGrading(int minutes){
        this.minutes = minutes;
    }

    public int getMinutes(){
        return minutes;
    }
}
