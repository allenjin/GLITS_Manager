package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.domain.GlRack;
import com.grandland.glits.ms.exception.FieldEmptyException;
import com.grandland.glits.ms.json.OperationResult;
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

/**
 * RackController
 * about rack's crud
 *
 * @author Allen Jin
 * @date 2016/01/26
 */

@Controller
@RequestMapping("/rack")
public class GlRackController {

    private static final Logger LOG = LoggerFactory.getLogger(GlRackController.class);

    @Autowired
    private GlRackDAO glRackDAO;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OperationResult addRack(GlRack rack) throws FieldEmptyException {
        OperationResult<GlRack> result = new OperationResult();
        if (rack != null) {
            if (StringUtil.isEmpty(rack.getRackName())) {
                throw new FieldEmptyException("机架名称不能为空");
            } else {
                GlRack glRack = glRackDAO.save(rack);
                result.setHasError(false);
                result.setMessage(MessageUtil.SUCCESS_OP);
                result.setBean(glRack);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public OperationResult delRack(@RequestParam("id") int id) {
        OperationResult result = new OperationResult();
        glRackDAO.delete(id);
        result.setHasError(false);
        result.setMessage(MessageUtil.SUCCESS_DELETE);
        return result;
    }
}
