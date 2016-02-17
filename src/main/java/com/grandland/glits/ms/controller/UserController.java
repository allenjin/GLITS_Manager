package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.UserDAO;
import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.exception.FieldEmptyException;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.UserService;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * AdminController
 *
 * @author Allen Jin
 * @date 2016/01/29
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String delUsers(@RequestParam("ids") List<Long> ids, RedirectAttributes attributes) {
        userService.deleteUsers(ids);
        attributes.addFlashAttribute("result", new OperationResult(false, MessageUtil.SUCCESS_DELETE));
        return "redirect:/settings/users";
    }

    @ResponseBody
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public OperationResult resetPassword(@RequestParam("id") long id) {
        User user = userDAO.findOne(id);
        userService.resetPassword(user);
        return new OperationResult(false, MessageUtil.SUCCESS_OP);
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String changePassword(@RequestParam("primaryPassword") String primaryPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 RedirectAttributes attributes) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isSuccess = userService.changePassword(user, primaryPassword, newPassword);
        OperationResult result;
        if (isSuccess) {
            result = new OperationResult(false, MessageUtil.SUCCESS_OP);
        } else {
            result = new OperationResult(true, MessageUtil.ERROR_PASSWORD_WRONG);
        }
        attributes.addFlashAttribute("result", result);
        return "redirect:/self/password";
    }


}
