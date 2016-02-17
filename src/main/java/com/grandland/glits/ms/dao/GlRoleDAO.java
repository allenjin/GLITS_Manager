package com.grandland.glits.ms.dao;

import com.grandland.glits.ms.domain.GlRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * GlRoleDAO
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Repository
public interface GlRoleDAO extends JpaRepository<GlRole, Integer>{
    GlRole findByName(String name);
}
