package com.grandland.glits.ms.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * HostUnitUtil
 *
 * @author Allen Jin
 * @date 2016/01/04
 */
public class HostUnitUtil {

    //字节转GB
    public static double byteToGB(long bytes) {
        double result;
        result = bytes / 1024d / 1024d / 1024d;
        return (double) Math.round(result * 100) / 100;
    }

}
