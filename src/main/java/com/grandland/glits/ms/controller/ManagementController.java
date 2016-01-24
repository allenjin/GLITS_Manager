package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.domain.GlRack;
import com.grandland.glits.ms.json.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger LOG = LoggerFactory.getLogger(ManagementController.class);
    @Autowired
    private GlRackDAO glRackDAO;

    @Autowired
    private GlHostDAO glHostDAO;

    private static final String BASE_ROOT = "management/";


    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "management");
    }

    @RequestMapping("/")
    public String index() {
        return BASE_ROOT + "index";
    }

    @RequestMapping("/racks")
    public String racks(@RequestParam(name = "page", defaultValue = "0") int page, Map<String, Object> model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("page", glRackDAO.findAll(pageable));
        return BASE_ROOT + "racks";
    }

    @RequestMapping("/hosts")
    public String hosts(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("page", glHostDAO.findAll(pageable));
        return BASE_ROOT + "hosts";
    }

    @RequestMapping(name = "/rack/add", method = RequestMethod.POST)
    @ResponseBody
    public OperationResult addRack(@ModelAttribute GlRack rack) {
        OperationResult result = new OperationResult();

        LOG.debug(rack.toString());
        if (rack != null) {
            glRackDAO.save(rack);
        }
        return result;
    }

}
