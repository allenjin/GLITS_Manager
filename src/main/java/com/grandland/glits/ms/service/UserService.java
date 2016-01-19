package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 * Created by lwz on 2016/1/19.
 */
@Service
public class UserService {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDAO userDAO;

   //添加用户
    public void addUser(String username, String password){
        User u = new User();
        u.setName(username);
        u.setPassword(encoder.encode(password)); //对密码加密后存储
        u.setRole(User.Role.ROLE_USER);//默认用户角色
        userDAO.save(u);
    }

    /**
     * @param password 明文*/
    //修改密码
    public void changePassword(long userId, String password){
        User user = userDAO.findOne(userId);
        user.setPassword(encoder.encode(password));
        userDAO.save(user);
    }
}
