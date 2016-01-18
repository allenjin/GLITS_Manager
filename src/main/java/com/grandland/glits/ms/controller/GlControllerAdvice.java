package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.config.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ControllerAdvice
 *  add global variable in every @RequestMapping Model
 *
 * @author Allen Jin
 * @date 2016/01/15
 */
@ControllerAdvice
public class GlControllerAdvice {

    @Autowired
    private SiteConfig siteConfig;

    @ModelAttribute(value = "siteConfig")
    public SiteConfig siteConfig(){
        return siteConfig;
    }

}
