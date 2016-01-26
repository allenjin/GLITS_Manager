package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.domain.User;
import com.grandland.glits.ms.exception.AlreadyExistException;
import com.grandland.glits.ms.exception.FieldEmptyException;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.UserService;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * AdminController
 * Created by lwz on 2016/1/13.
 * 管理员操作
 */
@Controller
@RequestMapping("/sys/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/yhlb", method = RequestMethod.GET)
    public ModelAndView queryUser(@RequestParam(value = "page",required = false)Integer page,
                                  @RequestParam(value = "size", required = false)Integer size,
                                  @RequestParam(value = "name", required = false)String name,
                                  @RequestParam(value = "userRole",required = false)String userRole,
                                  @RequestParam(value = "isEnable",required = false)Integer isEnableo, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("sys/admin/user_list");
        if(page == null){
            page = 0;
        }
        if(size == null){
            size = 20;
        }
        Boolean isEnable = null;
        if(isEnableo != null){
            switch (isEnableo) {
                case 1:
                    isEnable = false;
                case 0:
                    isEnable = true;
            }
        }
        Map<String,Object> searchParams = new HashMap<>();
        User.Role role = null;
        if(request != null){
            if(userRole != null){//不为空说明前端传来了角色参数
                if(!userRole.equals("全部")){
                    role = User.Role.valueOf(userRole);
                }
            }
        }
        searchParams.put("name", name);
        searchParams.put("userRole", role);
        searchParams.put("isEanble",isEnable);
        Page<User> result = userService.queryUsers(searchParams,page,size);
        mav.addObject("page", result);
        mav.addObject("params", searchParams);
        mav.addObject("userRoles", User.Role.values());
        return mav;
    }

    //删除用户
    @ResponseBody
    @RequestMapping(value = "/yhlb/delete",method = RequestMethod.POST)
    public OperationResult deleteUser(@RequestParam("id")Long id){
        OperationResult result = new OperationResult();
        userService.deleteUser(id);
        result.setHasError(false);
        result.setMessage(MessageUtil.SUCCESS_DELETE);
        return result;

    }

    //重置密码
    @ResponseBody
    @RequestMapping(value = "/yhlb/rspw",method = RequestMethod.POST)
    public OperationResult resetPassword(@RequestParam("id")Long id) {
        OperationResult result = new OperationResult();
        User user = userService.findUser(id);
        userService.changePassword(id, user.getName());
        result.setHasError(false);
        result.setMessage(MessageUtil.SUCESS_RESET);
        return result;
    }

    //激活或禁用用户
    @ResponseBody
    @RequestMapping(value = "/yhlb/enable",method = RequestMethod.POST)
    public OperationResult enableUser(@RequestParam("id")Long id,
                                @RequestParam("isEnable")boolean isEnable, RedirectAttributes attributes) {

        OperationResult result = new OperationResult();
        userService.enableUser(isEnable, id);
        result.setHasError(false);
        result.setMessage(MessageUtil.SUCCESS_OP);
        return result;

    }


    @RequestMapping(value="/adduser",method= RequestMethod.GET)
    public ModelAndView addUser(){
        ModelAndView mav = new ModelAndView("/sys/admin/user_add");
        mav.addObject("userRoles", User.Role.values());
        return mav;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView doAddUsers(@RequestParam(value="name") String name,
                                   @RequestParam(value="tel") String tel,
                                   @RequestParam(value="mail") String mail,
                                   @RequestParam(value="user-role")String userRoleStr)
            throws FieldEmptyException,AlreadyExistException{
        ModelAndView mav = new ModelAndView("/sys/admin/user_add");
        mav.addObject("userRoles", User.Role.values());
        OperationResult opResult = null;
        if(StringUtil.isEmpty(name)){
            throw new FieldEmptyException("用户名不能为空");
        }
        else if(StringUtil.isEmpty(tel)){
            throw new FieldEmptyException("联系电话不能为空");
        }
        else if(StringUtil.isEmpty(mail)){
            throw new FieldEmptyException("email不能为空");
        }
        else if(userService.isUserNameExists(name)){
            throw new AlreadyExistException("该用户名已存在，请换个用户名重试");
        }
        else{
            User.Role role = User.Role.valueOf(userRoleStr);
            userService.addUser(name, tel, mail, role);
            opResult = new OperationResult(false, MessageUtil.SUCCESS_REGISTER);

        }
        mav.addObject("opResult",opResult);
        return mav;
    }
}
