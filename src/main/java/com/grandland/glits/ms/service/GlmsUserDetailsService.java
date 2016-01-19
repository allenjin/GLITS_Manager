package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.ArrayList;

/**
 * CustomUserDetailsService
 * Created by lwz on 2016/1/12.
 */
public class GlmsUserDetailsService implements UserDetailsService{

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userDAO.findByName(username);

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        System.err.println("username is " + username + ", " + user.getRole().name());
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), authorities);
    }
}
