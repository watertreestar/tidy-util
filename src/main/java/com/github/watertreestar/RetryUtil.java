package com.github.watertreestar;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Utility for retry
 */
public class RetryUtil {
    /**
     *
     * @param method  调用的方法
     * @param retryCondition  重试的条件
     * @param retryInterval  重试间隔，ms
     * @param retryCount  重试次数
     * @param exceptionHandle  调用method的异常处理
     * @param exception  如果重试次数用完还是失败，抛出的异常
     * @param <U>
     * @return
     */
    public static <U> U invoke(Supplier<U> method, Predicate<U> retryCondition,
                               long retryInterval,int retryCount,
                                Function<Exception,U> exceptionHandle,
                               Supplier<? extends RuntimeException> exception){
        if(method == null || retryCondition == null || exception == null) {
            throw new IllegalArgumentException("Invoke method,retry condition and exception can not be null");
        }
        U result = null;
        try{
            result = method.get();
        }catch (Exception e) {
            if(exceptionHandle != null) {
                // 异常处理
                return exceptionHandle.apply(e);
            }
            throw e;
        }
        // 方法调用的结果不满足条件
        if(!retryCondition.test(result)){
            return result;
        }

        if(retryCount > 0){
            try {
                TimeUnit.MILLISECONDS.sleep(retryInterval);
            }catch (Exception e){}
            return invoke(method,retryCondition,retryInterval,retryCount-1,exceptionHandle,exception);
        }
        throw exception.get();
    }

}
