package com.grandland.glits.ms.service;

import com.grandland.glits.ms.dao.GlRackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
