package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.dao.GlRoleDAO;
import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlRack;
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
    private GlRackDAO glRackDAO;

    @Autowired
    private GlHostDAO glHostDAO;

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
        Pageable pageable = new PageRequest(page, 2);
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
    public String roles(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "roles");
        model.put("page", glRoleDAO.findAll(pageable));
        return BASE_ROOT + "roles";
    }

    @RequestMapping("/services")
    public String services(@RequestParam(name = "page", defaultValue = "0") int page, Map model) {
        Pageable pageable = new PageRequest(page, 15);
        model.put("activeTab", "services");
        model.put("page", glSerivceDAO.findAll(pageable));
        return BASE_ROOT + "services";
    }

    @ResponseBody
    @RequestMapping(value = "/rack/add", method = RequestMethod.POST)
    public OperationResult addRack(GlRack rack) {
        OperationResult result = new OperationResult();
        LOG.debug(rack.toString());
        if (rack != null) {
            if (StringUtil.isEmpty(rack.getRackName())) {
                result.setHasError(true);
                result.setMessage("机架名称不能为空");
            } else {
                try {
                    glRackDAO.save(rack);
                    result.setHasError(false);
                    result.setMessage(MessageUtil.SUCCESS_OP);
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    result.setHasError(true);
                    result.setMessage(MessageUtil.ERROR_SQL);
                }
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/rack/del", method = RequestMethod.POST)
    public OperationResult delRack(@RequestParam("id") int id) {
        OperationResult result = new OperationResult();
        try {
            glRackDAO.delete(id);
            result.setHasError(false);
            result.setMessage(MessageUtil.SUCCESS_DELETE);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setHasError(true);
            result.setMessage(MessageUtil.ERROR_SQL);
        }
        return result;
    }

}
