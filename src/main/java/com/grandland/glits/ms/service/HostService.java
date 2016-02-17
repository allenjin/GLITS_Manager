package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.GlHostDAO;
import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.dao.GlRoleDAO;
import com.grandland.glits.ms.domain.GlHost;
import com.grandland.glits.ms.form.HostForm;
import com.grandland.glits.ms.json.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HostService
 *
 * @author Allen Jin
 * @date 2016/02/16
 */

@Service
public class HostService {

    private static final Logger LOG = LoggerFactory.getLogger(HostService.class);

    @Autowired
    private GlHostDAO glHostDAO;

    @Autowired
    private GlRackDAO glRackDAO;

    @Autowired
    private GlRoleDAO glRoleDAO;

    public OperationResult<GlHost> saveHost(HostForm hostForm) {
        GlHost host = glHostDAO.findByHostName(hostForm.getHostName());
        String message = "修改机器成功";
        if (host == null) {
            host = new GlHost();
            host.setEnabled(true);
            host.setHostName(hostForm.getHostName());
            message = "添加机器成功";
        } else if (!hostForm.isUpdated()) {
            return new OperationResult<>(true, "机器名已经存在,请重新输入");
        }
        host.setIpAddress(hostForm.getIpAddress());
        host.setRack(glRackDAO.findOne(hostForm.getRackId()));
        host.setRoles(glRoleDAO.findAll(hostForm.getRoleIds()));
        glHostDAO.save(host);
        return new OperationResult<>(false, message);
    }

    public void deleteHosts(List<Integer> ids){
        if(ids == null){
            return;
        }
        List<GlHost> hosts = glHostDAO.findAll(ids);
        glHostDAO.delete(hosts);
    }

}
