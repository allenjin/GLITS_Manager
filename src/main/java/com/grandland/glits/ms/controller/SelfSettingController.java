package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.exception.FieldEmptyException;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.UserService;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * SelfSettingController
 * Created by lwz on 2016/1/19.
 */
@Controller
@RequestMapping("/sys/self")
public class SelfSettingController {

    private static final Logger LOG = LoggerFactory.getLogger(SelfSettingController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value = "/")
    public String selfIndex() {
        return "redirect:/sys/self/info";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView selfInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView("/sys/self/self_info");
        User user = userService.findUser(((User) userDetails).getId());
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ModelAndView doUpdateInfo(@RequestParam("tel") String tel,
                                     @RequestParam("mail") String mail) throws FieldEmptyException{
        ModelAndView mav = new ModelAndView("/sys/self/self_info");
        OperationResult opResult = null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUser(((User) userDetails).getId());
        if(StringUtil.isEmpty(tel)){
            throw new FieldEmptyException("联系电话不能为空");
        } else if(StringUtil.isEmpty(mail)){
            throw new FieldEmptyException("email不能为空");
        } else {
            user = userService.editUser(user,tel,mail);
            opResult = new OperationResult(false, MessageUtil.SUCCESS_UPDATE);
        }
        mav.addObject("opResult", opResult);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView changePassword() {
        ModelAndView mav = new ModelAndView("/sys/self/pwd_edit");
        return mav;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView doChangePassword(@RequestParam("origin-password") String oPassword,
                                         @RequestParam("password-1") String nPassword, @RequestParam("password-2") String nPassword2) {
        ModelAndView mav = new ModelAndView("/sys/self/pwd_edit");
        OperationResult opResult = null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.debug(userDetails.toString());
        if (nPassword.equals(nPassword2)) {
            if(encoder.matches(oPassword,userDetails.getPassword())){
                userService.changePassword(((User) userDetails).getId(), nPassword);
                opResult = new OperationResult(false, MessageUtil.SUCCESS_UPDATE);
            }else{
                opResult = new OperationResult(true, MessageUtil.ERROR_PASSWORD_WRONG);
            }
        } else {
            opResult = new OperationResult(true, MessageUtil.ERROR_PASSWORD_NOT_EQUAL);
        }
        mav.addObject("opResult", opResult);
        return mav;
    }
}
