package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * HomeController
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        return "redirect:/physical/";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println(System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        return "hello";
    }

}
