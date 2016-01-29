package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.form.UserForm;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * UserService
 * Created by lwz on 2016/1/19.
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDAO userDAO;

    /**
     * @param password 明文
     */
    public boolean changePassword(User user, String password, String newPassword) {
        if (encoder.matches(password, user.getPassword())) {
            user.setPassword(encoder.encode(newPassword));
            userDAO.save(user);
            return true;
        }
        return false;
    }

    public void resetPassword(User user) {
        user.setPassword(encoder.encode(user.getName()));
        userDAO.save(user);
    }

    public void deleteUsers(List<Long> ids) {
        if (ids == null) {
            return;
        }
        List<User> users = userDAO.findAll(ids);
        if (users != null) {
            for (User user : users) { //防止删除管理员
                if (user.getRole() == User.Role.ROLE_ADMIN) {
                    users.remove(user);
                }
            }
            userDAO.delete(users);
        }

    }

    public void updateSelf(String userName, UserForm userForm) {
        User user = userDAO.findByName(userName);
        user.setMail(userForm.getMail());
        user.setAvatar(userForm.getAvatar());
        user.setTel(userForm.getTel());
        userDAO.save(user);
    }

    public OperationResult<User> saveUser(UserForm userForm) {
        User user = userDAO.findByName(userForm.getName());
        String message = "修改用户成功";
        if (user == null) {
            user = new User();
            user.setPassword(encoder.encode(userForm.getName()));
            user.setName(userForm.getName());
            message = "创建新用户成功";
        } else if (!userForm.getUpdated()) {
            return new OperationResult<>(true, "用户名已经存在,请重新输入");
        }
        user.setTel(userForm.getTel());
        user.setMail(userForm.getMail());
        user.setRole(userForm.getUserRole());
        user.setIsEnable(true);
        user.setAvatar(userForm.getAvatar());
        userDAO.save(user);
        return new OperationResult<>(false, message, user);
    }

    public Page<User> queryUsers(UserForm userForm, int page, int size) {
        PageRequest pageRequest = buildPageRequest(page, size, "id");
        Specification<User> specification = buildSpecification(userForm);
        Page<User> result = userDAO.findAll(specification, pageRequest);
        return result;
    }

    private PageRequest buildPageRequest(int page, int size, String sortType) {
        return new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, sortType)));
    }

    private Specification<User> buildSpecification(final UserForm userForm) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                Path<String> name = root.get("name");
                Path<User.Role> role = root.get("role");
                Path<Boolean> isEnable = root.get("isEnable");
                if (!StringUtil.isEmpty(userForm.getName())) {
                    list.add(cb.equal(name, userForm.getName()));
                }
                if (userForm.getUserRole() != null) {
                    list.add(cb.equal(role, userForm.getUserRole()));
                }
                if (userForm.getEnabled() != null) {
                    list.add(cb.equal(isEnable, userForm.getEnabled()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));

            }
        };
    }
}
