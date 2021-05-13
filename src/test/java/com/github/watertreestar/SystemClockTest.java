package com.github.watertreestar;

import org.junit.Test;

import java.util.ArrayList;

public class SystemClockTest {

    @Test
    public void test(){
        long now = SystemClock.now();
        System.out.println(now);
        SystemClock.now();
        SystemClock.now();
        SystemClock.now();
        SystemClock.now();
        SystemClock.now();
        SystemClock.now();
    }
}
