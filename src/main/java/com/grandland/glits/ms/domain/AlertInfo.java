package com.grandland.glits.ms.domain;

import com.grandland.glits.ms.monitor.alert.AlertLevel;
import com.grandland.glits.ms.monitor.alert.AlertType;

import javax.persistence.*;
import java.util.Date;

/**
 * AlertInfo
 *
 * @author Allen Jin
 * @date 2016/01/12
 */

public class AlertInfo {

    @Id
    private long id;

    @Column(nullable = false)
    private AlertLevel level;

    @Column(nullable = false)
    private AlertType type;

    @Column(name = "host_name")
    private String hostName;

    @Column(nullable = false)
    private String info;

    private boolean closed = false;

    private Date time;
}
