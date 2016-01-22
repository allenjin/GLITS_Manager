package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TimedTaskController
 *
 * @author Allen Jin
 * @date 2016/01/22
 */

@Controller
@RequestMapping("/timed-task")
public class TimedTaskController {
    @RequestMapping("/")
    public String index(){
        return "timed-task/index";
    }
}
