package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.domain.GlRack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * ManagementController
 *
 * @author Allen Jin
 * @date 2016/01/22
 */

@Controller
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private GlRackDAO glRackDAO;

    @Autowired
    private GlHostDAO glHostDAO;

    private static final String BASE_ROOT = "management/";

    @RequestMapping("/")
    public String index() {
        return BASE_ROOT + "index";
    }

    @RequestMapping("/racks")
    public String racks(Map<String, Object> model) {
        model.put("racks", glRackDAO.findAll());
        return BASE_ROOT + "racks";
    }

    @RequestMapping("/hosts")
    public String hosts(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("hosts", glHostDAO.findAll(pageable));
        return BASE_ROOT + "hosts";
    }
}
