package com.grandland.glits.ms.dao;

import com.grandland.glits.ms.domain.GlRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GlRoleDAO
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Repository
public interface GlRackDAO extends JpaRepository<GlRack, Integer>{

    @Query("SELECT distinct r from GlRack r join fetch r.hosts")
    public List<GlRack> findAllRacksWithHosts();
}
