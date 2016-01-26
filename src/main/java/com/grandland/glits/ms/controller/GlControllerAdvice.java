package com.grandland.glits.ms.controller;

import com.grandland.glits.ms.config.SiteConfig;
import com.grandland.glits.ms.exception.FieldEmptyException;
import com.grandland.glits.ms.json.OperationResult;
import com.grandland.glits.ms.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * ControllerAdvice
 * add global variable in every @RequestMapping Model
 *
 * @author Allen Jin
 * @date 2016/01/15
 */
@ControllerAdvice
public class GlControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(GlControllerAdvice.class);

    @Autowired
    private SiteConfig siteConfig;

    @ModelAttribute(value = "siteConfig")
    public SiteConfig siteConfig() {
        return siteConfig;
    }

    @ExceptionHandler({DataAccessException.class})
    @ResponseBody
    public OperationResult handleDataAccessException(DataAccessException ex) {
        OperationResult result = new OperationResult();
        result.setHasError(true);
        result.setMessage(MessageUtil.ERROR_SQL);
        LOG.error("DataAccessException:{}", ex.getMessage(), ex);
        return result;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseBody
    public OperationResult handleViolationException(DataIntegrityViolationException ex) {
        OperationResult result = new OperationResult();
        result.setHasError(true);
        result.setMessage(MessageUtil.ERROR_VIOLATION);
        LOG.error("DataIntegrityViolationException:{}", ex.getMessage(), ex);
        return result;
    }

    @ExceptionHandler({FieldEmptyException.class})
    @ResponseBody
    public OperationResult handleFieldEmptyException(FieldEmptyException ex) {
        OperationResult result = new OperationResult();
        result.setHasError(true);
        result.setMessage(ex.getMessage());
        LOG.error("FieldEmptyException: {}", ex.getMessage(), ex);
        return result;
    }

}
