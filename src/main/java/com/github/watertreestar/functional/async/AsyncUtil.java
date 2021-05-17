package com.github.watertreestar.functional.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class  AsyncUtil {

    private static Executor executor = Executors.newCachedThreadPool();

    /**
     * 异步执行方法
     * @param <R>
     * @return
     */
    public static <R,T> void doAsync(Supplier<T> taskBefore, Supplier<R> task, Consumer<R> taskAfter) {
        executor.execute(new AsyncTask<T,R>(taskBefore,task,taskAfter));
    }


    private static class AsyncTask<T,R> extends Thread {
        // before task callback
        private Supplier<T> taskBefore;
        // main task
        private Supplier<R> task;
        // after task callback
        private Consumer<R> taskAfter;

        public  AsyncTask(Supplier<T> taskBefore, Supplier<R> task, Consumer<R> taskAfter){
            this.taskBefore = taskBefore;
            this.task = task;
            this.taskAfter = taskAfter;
        }

        @Override
        public void run(){
            taskBefore.get();
            R result = task.get();
            taskAfter.accept(result);
        }
    }
}
