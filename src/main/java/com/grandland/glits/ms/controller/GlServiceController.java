package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlService;
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
 * GlServiceController
 *
 * @author Allen Jin
 * @date 2016/01/26
 */
@Controller
@RequestMapping("/service")
public class GlServiceController {
    private static final Logger LOG = LoggerFactory.getLogger(GlServiceController.class);

    @Autowired
    private GlSerivceDAO glSerivceDAO;

    @ResponseBody
    @RequestMapping("/add")
    public OperationResult addService(GlService service) {
        OperationResult<GlService> result = new OperationResult();
        if (service != null) {
            if (StringUtil.isEmpty(service.getName()) || StringUtil.isEmpty(service.getDisplayName())) {
                result.setHasError(true);
                result.setMessage("服务显示名称或服务名不能为空");
            } else {
                GlService glService = glSerivceDAO.save(service);
                result.setHasError(false);
                result.setMessage("添加服务成功");
                result.setBean(glService);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/del")
    public OperationResult delService(@RequestParam("id") Integer id) {
        OperationResult result = new OperationResult();
        if (id != null) {
            glSerivceDAO.delete(id);
            result.setHasError(false);
            result.setMessage("删除服务成功");
        }
        return result;
    }
}
