package com.grandland.glits.ms.agent;

import com.grandland.glits.ms.Application;
import org.joda.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * HeartbeatTest
 *
 * @author Allen Jin
 * @date 2015/12/19
 */
@RunWith(JUnit4.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
public class HeartbeatTest {

    @Test
    public void testTime(){
        Instant instant = new Instant();
        System.out.println(instant.toString());
    }
}
