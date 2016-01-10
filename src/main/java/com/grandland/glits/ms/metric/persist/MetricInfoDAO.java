package com.grandland.glits.ms.metric.persist;

import com.grandland.glits.ms.metric.common.MetricGrading;
import com.grandland.glits.ms.metric.common.MonitoringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MetricInfoDAO
 *
 * @author Allen Jin
 * @date 2016/01/10
 */

@Repository
public interface MetricInfoDAO extends JpaRepository<MetricInfo, Long> {

    public Page<MetricInfo> findByHostNameAndTypeAndGrading(String hostName, MonitoringType type, MetricGrading grading, Pageable pageable);
}
