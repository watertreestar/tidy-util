package com.github.watertreestar.minilog;

import com.github.watertreestar.minlog.Log;
import org.junit.Test;

public class LogTest {
    @Test
    public void testLog() {
        Log.debug("debug info");
        Log.info("test","debug info");
        Log.error("error info");
        Log.trace("trace info");
        Log.warn("debug info");

        Log.set(Log.LEVEL_TRACE);

        Log.debug("debug info");
        Log.info("test","debug info");
        Log.error("error info");
        Log.trace("trace info");
        Log.warn("debug info");
    }
}
