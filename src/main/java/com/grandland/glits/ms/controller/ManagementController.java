package com.grandland.glits.ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.dao.GlRoleDAO;
import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.domain.GlRack;
import com.grandland.glits.ms.domain.GlRole;
import com.grandland.glits.ms.form.HostForm;
import com.grandland.glits.ms.form.RoleForm;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.GLRoleService;
import com.grandland.glits.ms.service.HostService;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private HostService hostService;

    @Autowired
    private GLRoleService roleService;

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
        Pageable pageable = new PageRequest(page, 20);
        model.put("activeTab", "hosts");
        model.put("page", glHostDAO.findAll(pageable));
        return BASE_ROOT + "hosts";
    }

    @RequestMapping("/host/add")
    public String addHost(Map<String, Object> model) throws JsonProcessingException {
        model.put("activeTab", "hosts");
        ObjectMapper mapper = new ObjectMapper();
        model.put("rackList", mapper.writeValueAsString(glRackDAO.findAll()));
        model.put("roleList", mapper.writeValueAsString(glRoleDAO.findAll()));
        return BASE_ROOT + "host_add";
    }

    @RequestMapping(value = "/host/save", method = RequestMethod.POST)
    public String saveHost(HostForm hostForm, BindingResult bindingResult, RedirectAttributes attributes) {
        LOG.debug(hostForm.toString());
        if (bindingResult.hasErrors()) {
            return BASE_ROOT + "host_add";
        }
        OperationResult<GlHost> result = hostService.saveHost(hostForm);
        attributes.addFlashAttribute("result", result);
        return "redirect:/management/hosts";
    }

    @RequestMapping("/host/modify")
    public String modifyHost(@RequestParam("id") int id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("host", glHostDAO.findOne(id));
        attributes.addFlashAttribute("isUpdated", true);
        return "redirect:/management/host/add";
    }

    @RequestMapping("/roles")
    public String roles(@RequestParam(name = "page", defaultValue = "0") int page, Map model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Pageable pageable = new PageRequest(page, 20);
        model.put("activeTab", "roles");
        model.put("page", glRoleDAO.findAll(pageable));
        return BASE_ROOT + "roles";
    }

    @RequestMapping("/role/add")
    public String addRole(Map<String, Object> model) throws JsonProcessingException {
        model.put("activeTab", "roles");
        ObjectMapper mapper = new ObjectMapper();
        model.put("serviceList", mapper.writeValueAsString(glSerivceDAO.findAll()));
        model.put("categoryList", GlRole.PsCategory.values());
        return BASE_ROOT + "role_add";
    }

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public String saveRole(RoleForm roleForm, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return BASE_ROOT + "role_add";
        }
        OperationResult<GlRole> result = roleService.saveRole(roleForm);
        attributes.addFlashAttribute("result", result);
        return "redirect:/management/roles";
    }

    @RequestMapping("/role/modify")
    public String modifyRole(@RequestParam("id") int id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("role", glRoleDAO.findOne(id));
        attributes.addFlashAttribute("isUpdated", true);
        return "redirect:/management/role/add";
    }

    @RequestMapping("/services")
    public String services(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "services");
        model.put("page", glSerivceDAO.findAll(pageable));
        return BASE_ROOT + "services";
    }


}
