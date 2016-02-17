package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.GLRoleService;
import com.grandland.glits.ms.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * GlRoleController
 *
 * @author Allen Jin
 * @date 2016/01/26
 */
@Controller
@RequestMapping("/role")
public class GlRoleController {
    private static final Logger LOG = LoggerFactory.getLogger(GlRoleController.class);

    @Autowired
    private GLRoleService roleService;

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String delHosts(@RequestParam("ids") List<Integer> ids, RedirectAttributes attributes) {
        roleService.deleteRoles(ids);
        attributes.addFlashAttribute("result", new OperationResult(false, MessageUtil.SUCCESS_DELETE));
        return "redirect:/management/roles";
    }


}
