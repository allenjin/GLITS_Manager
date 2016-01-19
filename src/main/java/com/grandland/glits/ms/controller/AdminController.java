package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminController
 * Created by lwz on 2016/1/13.
 * 管理员操作
 */
@Controller
@RequestMapping("/sys/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/adduser",method= RequestMethod.GET)
    public ModelAndView addUser(){
        ModelAndView mav = new ModelAndView("/sys/admin/user_add");
        return mav;
    }

    @RequestMapping(value="/adduser",method= RequestMethod.POST)
    public ModelAndView addUser(String username, String password){
        ModelAndView mav = new ModelAndView("/sys/admin/user_add");
        userService.addUser(username,password);
        return mav;
    }
}
