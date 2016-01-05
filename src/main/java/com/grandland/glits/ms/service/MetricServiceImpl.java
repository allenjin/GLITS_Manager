package com.grandland.glits.ms.service;

import com.grandland.glits.ms.protocol.MetricMessage;
import com.grandland.glits.ms.protocol.MetricService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static  final Logger LOG = LoggerFactory.getLogger(MetricServiceImpl.class);

    @Override
    public void sendMetricMessage(List<MetricMessage> messages) throws TException {
        LOG.debug(messages.toString());
    }
}
