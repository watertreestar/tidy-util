package com.github.watertreestar.executor;

import com.github.watertreestar.thread.ThreadPoolBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 可以同步，异步，延时执行的
 */
public class TidyExecutor {
    private static volatile TidyExecutor INSTANCE = null;
    private static AtomicReference<ExecutorService> es = new AtomicReference<>();
    private static AtomicReference<ScheduledExecutorService> ses = new AtomicReference<>();

    /**
     * Number of CPU
     */
    private static final int N_CPUS = Runtime.getRuntime().availableProcessors();
    /**
     * IO密集型任务的并发线程数
     */
    public static final int N_THREADS_IO_INTENSIVE = N_CPUS * 2;
    /**
     * 计算密集型任务的并发线程数
     */
    public static final int N_THREADS_COMPUTE_INTENSIVE = N_CPUS;

    public static TidyExecutor instance() {
        if (INSTANCE == null) {
            synchronized (TidyExecutor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TidyExecutor();
                }
            }
        }
        return INSTANCE;
    }

    private TidyExecutor() {
        es.set(ThreadPoolBuilder.newThreadPool(
                "TidyExecutor", // 线程池名
                500, // 线程池大小
                60, // 空闲线程存活时间(s)
                Integer.MAX_VALUE)); // 工作队列容量

        ses.set(ThreadPoolBuilder.newScheduleThreadPool(
                "TidyScheduledExecutor",
                N_THREADS_IO_INTENSIVE
        ));
    }

    /**
     * 同步执行
     *
     * @param task
     * @param request
     * @param <U>
     * @param <V>
     * @return
     */
    public <U, V> V sync(final AbstractTask<U, V> task, final U request) {
        Future<V> future = async(task, request);
        return get(task, request, future);
    }

    /**
     * 异步执行
     */
    public <U, V> Future<V> async(final AbstractTask<U, V> task, final U request) {
        return es.get().submit(new Callable<V>() {
            @Override
            public V call() throws Exception {
                return task.execute(request);
            }
        });
    }

    /**
     * 异步执行结果(+超时/失败重试)
     */
    public <U, V> V get(final AbstractTask<U, V> task, final U request, Future<V> future) {
        V ret = null;
        try {
            ret = future.get(task.getTimeoutSeconds(), TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            future.cancel(true);
            switch (task.getTimeoutPolicy()) {
                case AbstractTask.TERMINATED:
                    task.setCompleted();
                    break;
                case AbstractTask.RETRY:   // 重试，状态置为失败
                    task.setFailed();
                    break;
                default:
                    break;
            }
        }

        if (task.getStatus() == AbstractTask.FAILED) {
            Long retryDelaySeconds = check4Retry(task);
            if (retryDelaySeconds > 0) {
                return sync(task, request);
            }
        }
        return ret;
    }

    /**
     * 延迟异步执行
     */
    public <U, V> void delay(final AbstractTask<U, V> task, final U request) {
        long delaySeconds = task.getDelaySeconds();
        if (delaySeconds <= 0)
            throw new IllegalArgumentException("task arg: delay seconds illegal.");
        ses.get().schedule(new Runnable() {
            @Override
            public void run() {
                task.execute(request);
            }
        }, delaySeconds, TimeUnit.SECONDS);
    }

    /**
     * 计算下次重试的时间
     */
    private <U, V> Long check4Retry(AbstractTask<U, V> task) {
        long ret = 0;
        long retriedCount = task.getRetriedCount();
        if (retriedCount < task.getRetryCount()) {
            task.setRetriedCount(retriedCount + 1);
            long retryDelaySeconds = task.getRetryDelaySeconds();
            if (retryDelaySeconds > 0) {
                // Retry Logic
                // retry with a delay
                switch (task.getRetryLogic()) {
                    case AbstractTask.RETRY_BACKOFF:
                        retryDelaySeconds = retryDelaySeconds * (1 + task.getRetriedCount());
                        break;
                    default:
                        break;
                }
            }
            ret = retryDelaySeconds;
        }
        return ret;
    }
}
