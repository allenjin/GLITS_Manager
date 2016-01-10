package com.grandland.glits.ms.metric.utils;

import com.grandland.glits.ms.metric.common.MetricType;
import com.grandland.glits.ms.protocol.MetricValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MetricUtil
 *
 * @author Allen Jin
 * @date 2016/01/10
 */
public class MetricUtil {

    public static Map extracMetricValues(List<MetricValue> metricValueList){
        Map map = new HashMap();
        for(MetricValue value : metricValueList){
            putMetricValue(map, value);
        }

        return map;
    }

    private static void putMetricValue(Map map, MetricValue value){
        switch (value.getType()){
            case MetricType.INT:
                map.put(value.getId(), Integer.valueOf(value.getValue()));
                break;
            case MetricType.LONG:
                map.put(value.getId(), Long.valueOf(value.getValue()));
                break;
            case MetricType.DOUBLE:
                map.put(value.getId(), Double.valueOf(value.getValue()));
                break;

            default:
                map.put(value.getId(), value.getValue());
        }
    }

}
