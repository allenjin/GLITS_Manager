package com.grandland.glits.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * DataAccessController
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Controller
@RequestMapping("/data-access")
public class DataAccessController {

    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "data-access");
    }

    @RequestMapping("/")
    public String index() {
        return "data-access/index";
    }
}
