package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.form.UserForm;
import com.grandland.glits.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * SettingsController
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
        LOG.debug(userForm.toString());
        Page<User> result = userService.queryUsers(userForm, page, 15);
        model.put("page", result);
        model.put("userForm", userForm);
        model.put("userRoles", User.Role.values());
        return BASE_ROOT + "user/users";
    }
}
