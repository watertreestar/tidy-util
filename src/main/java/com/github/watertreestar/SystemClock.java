package com.github.watertreestar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 当前时间获取
 */
public class SystemClock {
    private int period;

    private AtomicLong clock;

    public SystemClock(int period) {
        this.period = period;
        this.clock = new AtomicLong(System.currentTimeMillis());
        scheduleAtFixedRate();
    }

    public static class SystemClockHolder {
        private static final SystemClock INSTANCE = new SystemClock(1);
    }

    private void scheduleAtFixedRate() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });
        executor.scheduleAtFixedRate(() -> this.clock.set(System.currentTimeMillis()), period, period, TimeUnit.MILLISECONDS);
    }

    private static SystemClock instance() {
        return SystemClockHolder.INSTANCE;
    }

    public static long now() {
        return instance().clock.get();
    }
}
