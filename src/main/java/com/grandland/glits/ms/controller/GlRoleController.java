package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlRoleDAO;
import com.grandland.glits.ms.domain.GlRole;
import com.grandland.glits.ms.exception.FieldEmptyException;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private GlRoleDAO glRoleDAO;

    @ResponseBody
    @RequestMapping("/add")
    public OperationResult addRole(GlRole role) throws FieldEmptyException {
        OperationResult<GlRole> result = new OperationResult();
        if (role != null) {
            if (StringUtil.isEmpty(role.getName()) || StringUtil.isEmpty(role.getDisplayName())) {
                throw new FieldEmptyException("角色名或进程名称不能为空");
            }
            GlRole glRole = glRoleDAO.save(role);
            result.setHasError(false);
            result.setMessage(MessageUtil.SUCCESS_OP);
            result.setBean(glRole);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/del")
    public OperationResult delRole(@RequestParam("id") Integer id) {
        OperationResult result = new OperationResult();
        if (id != null) {
            glRoleDAO.delete(id);
            result.setHasError(false);
            result.setMessage(MessageUtil.SUCCESS_DELETE);
        }
        return result;
    }


}
