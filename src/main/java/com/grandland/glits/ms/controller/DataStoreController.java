package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DataStoreController
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Controller
@RequestMapping("/data-store")
public class DataStoreController {

    @RequestMapping("/")
    public String index(){
        return "data-store/index";
    }
}
