package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * DataStoreController
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Controller
@RequestMapping("/data-store")
public class DataStoreController {

    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "data-store");
    }

    @RequestMapping("/")
    public String index() {
        return "data-store/index";
    }
}
