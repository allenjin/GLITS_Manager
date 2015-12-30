package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HomeController
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Controller
public class HomeController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

}
