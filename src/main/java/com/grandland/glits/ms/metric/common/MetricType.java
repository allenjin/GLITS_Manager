package com.grandland.glits.ms.metric.common;

/**
 * MetricType
 *
 * @author Allen Jin
 * @date 2016/01/06
 */
public enum MetricType {
    STRING(0), INT(1), LONG(2), DOUBLE(3);

    private int code;

    MetricType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
