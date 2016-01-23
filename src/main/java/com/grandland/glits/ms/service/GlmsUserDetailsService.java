package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;

/**
 * CustomUserDetailsService
 * Created by lwz on 2016/1/12.
 */
public class GlmsUserDetailsService implements UserDetailsService{

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("该用户名不存在");
        }else if(!user.isEnabled()){
            throw new DisabledException("该用户不可用");
        }
        user.setLastLogin(new Date());
        userDAO.save(user);
        return user;
    }
}
