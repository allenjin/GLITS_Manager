package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public User addUser(String name, String tel, String mail, User.Role role){
        User u = new User();
        u.setName(name);
        u.setTel(tel);
        u.setMail(mail);
        u.setPassword(encoder.encode("123")); //对密码加密后存储
        u.setIsEnable(true);
        u.setRole(role);
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

    //激活或者禁用用户
    public void enableUser(boolean isEnable, long userId){
        User user = userDAO.findOne(userId);
        user.setIsEnable(!isEnable);
        userDAO.save(user);
    }
    public boolean isUserNameExists(String name){
        UserDetails user = userDAO.findByName(name);
        return user != null ;
    }

    public User save(User user){
        return  userDAO.save(user);
    }

    public User findUser(long id){
        return userDAO.findOne(id);
    }

    public User editUser(User user, String tel, String mail){
        user.setTel(tel);
        user.setMail(mail);
        return save(user);
    }

    public Page<User> queryUsers(Map seachParams, int page, int size) {
        PageRequest pageRequest = buildPageRequest(page, size, "id");
        Specification<User> specification = buildSpecification(seachParams);
        Page<User> result = userDAO.findAll(specification, pageRequest);
        return  result;
    }

    private  PageRequest buildPageRequest(int page, int size, String sortType){
        return new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC,sortType)));
    }

    private Specification<User> buildSpecification(final Map<String, Object> searchParams){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                Path<String> name = root.get("name");
                Path<User.Role> role = root.get("role");
                Path<Boolean> isEnable = root.get("isEnable");
                if(searchParams.get("name") != null && !searchParams.get("name").equals("")){
                    list.add(cb.equal(name, searchParams.get("name")));
                }
                if(searchParams.get("userRole") != null){
                    list.add(cb.equal(role, searchParams.get("userRole")));
                }
                if(searchParams.get("isEnable") != null){
                    list.add(cb.equal(isEnable, searchParams.get("isEnable")));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));

            }
        };
    }

    public void deleteUser(long id ){
        userDAO.delete(id);
    }
}
