package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.UserService;
import com.grandland.glits.ms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView doAddUsers(@RequestParam(value="name") String name,
                                   @RequestParam(value="tel") String tel,
                                   @RequestParam(value="mail") String mail){
        ModelAndView mav = new ModelAndView("/sys/admin/user_add");

        OperationResult opResult = null;
        if(name.trim().equals("")){
            opResult = new OperationResult(true, "用户名不能为空");
        }
        else if(tel.trim().equals("")){
            opResult = new OperationResult(true, "联系电话不能为空");
        }
        else if(mail.trim().equals("")){
            opResult = new OperationResult(true, "email不能为空");
        }
        else if(userService.isUserNameExists(name)){
            opResult = new OperationResult(true, "该用户名已存在，请换个用户名重试");
        }
        else{
            try{
                User r= userService.addUser(name, tel, mail);
                opResult = new OperationResult(false, MessageUtil.SUCCESS_REGISTER);
            } catch (Exception e){
                opResult = new OperationResult(true, MessageUtil.ERROR_SQL);
            }
        }
        mav.addObject("opResult",opResult);
        return mav;
    }
}
