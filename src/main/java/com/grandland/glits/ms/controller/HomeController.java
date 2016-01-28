package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/physical/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }

    @RequestMapping("/404")
    public String notFound() {
        return "404";
    }


}

