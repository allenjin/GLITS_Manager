package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DataStreamController
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Controller
@RequestMapping("data-stream")
public class DataStreamController {

    @RequestMapping("/")
    public String index(){
        return "data-stream/index";
    }
}
