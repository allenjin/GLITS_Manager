package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.GlRackDAO;
import com.grandland.glits.ms.domain.GlRack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RackService
 *
 * @author Allen Jin
 * @date 2016/01/11
 */

@Service
public class RackService {

    @Autowired
    private GlRackDAO glRackDAO;

    public List<GlRack> queryRacks(){
        return glRackDAO.findAllRacksWithHosts();
    }
}
