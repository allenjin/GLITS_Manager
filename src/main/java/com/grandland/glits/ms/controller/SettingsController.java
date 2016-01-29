package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.form.UserForm;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

/**
 * SettingsController
 * 系统设置
 *
 * @author Allen Jin
 * @date 2016/01/28
 */

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private static final Logger LOG = LoggerFactory.getLogger(SettingsController.class);
    private static final String BASE_ROOT = "settings/";

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "settings");
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/settings/users";
    }

    @RequestMapping("/users")
    public String Users(UserForm userForm,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        Map<String, Object> model) {
        Page<User> result = userService.queryUsers(userForm, page, 15);
        model.put("activeTab", "users");
        model.put("page", result);
        model.put("userForm", userForm);
        model.put("userRoles", User.Role.values());
        return BASE_ROOT + "user/users";
    }

    @RequestMapping("/user/add")
    public String addUser(Map<String, Object> model) {
        model.put("activeTab", "users");
        model.put("userRoles", User.Role.values());
        return BASE_ROOT + "user/user_add";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveUser(UserForm userForm, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return BASE_ROOT + "user/user_add";
        }
        OperationResult<User> result = userService.saveUser(userForm);
        attributes.addFlashAttribute("result", result);
        return "redirect:/settings/users";
    }

    @RequestMapping("/user/modify")
    public String modifyUser(@RequestParam("id") long id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("user", userDAO.findOne(id));
        attributes.addFlashAttribute("isUpdated", true);
        return "redirect:/settings/user/add";
    }

}
