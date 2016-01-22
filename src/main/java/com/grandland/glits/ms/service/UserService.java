package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    public User addUser(String name, String tel, String mail){
        User u = new User();
        u.setName(name);
        u.setTel(tel);
        u.setMail(mail);
        u.setPassword(encoder.encode("123")); //对密码加密后存储
        u.setRole(User.Role.ROLE_USER);//默认用户角色
        userDAO.save(u);
        return u;
    }

    /**
     * @param password 明文*/
    //修改密码
    public void changePassword(long userId, String password){
        User user = userDAO.findOne(userId);
        user.setPassword(encoder.encode(password));
        userDAO.save(user);
    }

    public boolean isUserNameExists(String name){
        UserDetails user = userDAO.findByName(name);
        return user != null ;
    }

    public void save(User user){
        userDAO.save(user);
    }

    public User findUser(long id){
        return userDAO.findOne(id);
    }
}
