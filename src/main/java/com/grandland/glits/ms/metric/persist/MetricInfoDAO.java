package com.grandland.glits.ms.metric.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MetricInfoDAO
 *
 * @author Allen Jin
 * @date 2016/01/10
 */

@Repository
public interface MetricInfoDAO extends JpaRepository<MetricInfo,Long>{

}
