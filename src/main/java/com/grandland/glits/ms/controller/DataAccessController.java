package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DataAccessController
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Controller
@RequestMapping("/data-access")
public class DataAccessController {

    @RequestMapping("/")
    public String index(){
        return "data-access/index";
    }
}
