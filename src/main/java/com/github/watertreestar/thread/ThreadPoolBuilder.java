package com.github.watertreestar.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolBuilder {
    public static ExecutorService newThreadPool(final String threadName, int poolSize, long keepAliveTime,
                                                int workQueueCapacity) {
        ThreadPoolExecutor ret = new ThreadPoolExecutor(poolSize, poolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(workQueueCapacity), new ThreadFactory() {
            AtomicInteger ai = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread ret = new Thread(r, threadName + "-Thread-" + ai.getAndIncrement());
                return ret;
            }
        }, new AbortPolicyHandler(threadName));
        ret.allowCoreThreadTimeOut(true);
        return ret;
    }

    /**
     * 构造一个ScheduleExecutorService
     *
     * @param threadName
     * @param poolSize
     * @return
     */
    public static ScheduledExecutorService newScheduleThreadPool(final String threadName, int poolSize) {
        return Executors.newScheduledThreadPool(poolSize, new ThreadFactory() {
            AtomicInteger ai = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread ret = new Thread(r, threadName + "-Thread-" + ai.getAndIncrement());
                return ret;
            }
        });
    }
}
