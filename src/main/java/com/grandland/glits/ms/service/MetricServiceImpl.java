package com.grandland.glits.ms.service;

import com.grandland.glits.ms.protocol.MetricMessage;
import com.grandland.glits.ms.protocol.MetricService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MetricServiceImpl
 *
 * @author Allen Jin
 * @date 2016/01/04
 */

@Service
public class MetricServiceImpl implements MetricService.Iface {

    @Override
    public void sendMetricMessage(List<MetricMessage> messages) throws TException {

    }
}
