package com.github.watertreestar;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;


public class SystemClockTest {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    @PerfTest(threads = 10, duration = 3500)
    public void test(){
        long now = SystemClock.now();
        System.out.println(now);

        /**
         * result:
         * samples: 174575
         * max:     361
         * average: 20.401191464986397
         * median:  0
         */
    }

    @Test
    @PerfTest(threads = 10, duration = 3500)
    public void testNative(){
        long time = System.currentTimeMillis();
        System.out.println(time);
    }
}
