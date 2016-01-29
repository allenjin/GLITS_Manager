package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.form.UserForm;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

/**
 * SelfController
 * 个人管理
 *
 * @author Allen Jin
 * @date 2016/01/29
 */
@Controller
@RequestMapping("/self")
public class SelfController {

    private static final Logger LOG = LoggerFactory.getLogger(SelfController.class);
    private static final String BASE_ROOT = "self/";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "self");
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/self/info";
    }

    @RequestMapping("/info")
    public String info(Map<String, Object> model) {
        model.put("activeTab", "info");
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("user", userDAO.findByName(user.getUsername()));
        return BASE_ROOT + "info";
    }

    @RequestMapping(value = "/info/save", method = RequestMethod.POST)
    public String saveInfo(@Valid UserForm userForm, BindingResult bindingResult, RedirectAttributes attributes) {
        LOG.debug(userForm.toString());
        if (bindingResult.hasErrors()) {
            return BASE_ROOT + "info";
        }
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateSelf(user.getUsername(), userForm);
        attributes.addFlashAttribute("result", new OperationResult<>(false, "修改成功"));
        return "redirect:/self/info";
    }

    @RequestMapping("/password")
    public String password(Map<String, Object> model) {
        model.put("activeTab", "info");
        return BASE_ROOT + "password";
    }

    @RequestMapping("/message")
    public String message(Map<String, Object> model) {
        model.put("activeTab", "message");
        return BASE_ROOT + "message_center";
    }
}
