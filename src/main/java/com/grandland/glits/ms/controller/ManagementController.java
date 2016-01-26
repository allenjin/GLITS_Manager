package com.grandland.glits.ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.dao.GlRoleDAO;
import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlRack;
import com.grandland.glits.ms.domain.GlRole;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
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
    private GlHostDAO glHostDAO;

    @Autowired
    private GlRackDAO glRackDAO;

    @Autowired
    private GlRoleDAO glRoleDAO;

    @Autowired
    private GlSerivceDAO glSerivceDAO;

    private static final String BASE_ROOT = "management/";

    @ModelAttribute
    public void putCommonParams(Map<String, Object> model) {
        model.put("menuItem", "management");
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/management/racks";
    }

    @RequestMapping("/racks")
    public String racks(@RequestParam(name = "page", defaultValue = "0") int page, Map<String, Object> model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "racks");
        model.put("page", glRackDAO.findAll(pageable));
        return BASE_ROOT + "racks";
    }

    @RequestMapping("/hosts")
    public String hosts(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "hosts");
        model.put("page", glHostDAO.findAll(pageable));
        return BASE_ROOT + "hosts";
    }

    @RequestMapping("/roles")
    public String roles(@RequestParam(name = "page", defaultValue = "0") int page, Map model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "roles");
        model.put("page", glRoleDAO.findAll(pageable));
        model.put("serviceList", mapper.writeValueAsString(glSerivceDAO.findAll()));
        model.put("categoryList", mapper.writeValueAsString(GlRole.PsCategory.values()));
        return BASE_ROOT + "roles";
    }

    @RequestMapping("/services")
    public String services(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "services");
        model.put("page", glSerivceDAO.findAll(pageable));
        return BASE_ROOT + "services";
    }


}
