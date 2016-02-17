package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.GlSerivceDAO;
import com.grandland.glits.ms.domain.GlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * GLServiceSerivce
 *
 * @author Allen Jin
 * @date 2016/02/17
 */

@Service
public class GLServiceSerivce {

    @Autowired
    private GlSerivceDAO glSerivceDAO;

    public void deleteServices(List<Integer> ids){
        if(ids == null){
            return;
        }
        List<GlService> racks = glSerivceDAO.findAll(ids);
        glSerivceDAO.delete(racks);
    }
}
