package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.GlRoleDAO;
import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlRole;
import com.grandland.glits.ms.form.RoleForm;
import com.grandland.glits.ms.json.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HostRoleService
 *
 * @author Allen Jin
 * @date 2016/02/17
 */

@Service
public class GLRoleService {

    @Autowired
    private GlRoleDAO glRoleDAO;

    @Autowired
    private GlSerivceDAO glSerivceDAO;

    public void deleteRoles(List<Integer> ids) {
        if (ids == null) {
            return;
        }
        List<GlRole> hosts = glRoleDAO.findAll(ids);
        glRoleDAO.delete(hosts);
    }

    public OperationResult<GlRole> saveRole(RoleForm roleForm) {
        GlRole role = glRoleDAO.findByName(roleForm.getName());
        String message = "修改角色成功";
        if (role == null) {
            role = new GlRole();
            role.setName(roleForm.getName());
            message = "添加角色成功";
        } else if (!roleForm.isUpdated()) {
            return new OperationResult<>(true, "角色名已经存在,请重新输入");
        }
        role.setDisplayName(roleForm.getDisplayName());
        role.setCategory(roleForm.getPsCategory());
        role.setDescription(roleForm.getDescription());
        role.setScript(roleForm.getScript());
        role.setRunning(roleForm.isRunning());
        role.setService(glSerivceDAO.findOne(roleForm.getServiceId()));
        role.setAutoRestart(roleForm.isAutoRestart());
        glRoleDAO.save(role);
        return new OperationResult<>(false, message);
    }

}
