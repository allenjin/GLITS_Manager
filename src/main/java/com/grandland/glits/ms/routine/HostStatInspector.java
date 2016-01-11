package com.grandland.glits.ms.routine;

import org.springframework.stereotype.Service;

/**
 * HostStatInspector
 *
 * @author Allen Jin
 * @date 2016/01/11
 */
@Service
public class HostStatInspector extends Thread{

    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while(isRunning){

        }
    }
}
