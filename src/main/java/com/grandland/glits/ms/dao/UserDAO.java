package com.grandland.glits.ms.dao;

import com.grandland.glits.ms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * GlRoleDAO
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Repository
public interface UserDAO extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{
    User findByName(String username);
}
