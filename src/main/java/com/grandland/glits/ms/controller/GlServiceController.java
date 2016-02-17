package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlService;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.service.GLServiceSerivce;
import com.grandland.glits.ms.utils.MessageUtil;
import com.grandland.glits.ms.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @Autowired
    private GLServiceSerivce serviceSerivce;

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

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String delServices(@RequestParam("ids") List<Integer> ids, RedirectAttributes attributes) {
        serviceSerivce.deleteServices(ids);
        attributes.addFlashAttribute("result", new OperationResult(false, MessageUtil.SUCCESS_DELETE));
        return "redirect:/management/services";
    }
}
