package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * TimedTaskController
 *
 * @author Allen Jin
 * @date 2016/01/22
 */

@Controller
@RequestMapping("/timed-task")
public class TimedTaskController {

    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "timed-task");
    }

    @RequestMapping("/")
    public String index(){
        return "timed-task/index";
    }
}
