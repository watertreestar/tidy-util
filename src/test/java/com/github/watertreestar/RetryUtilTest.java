package com.github.watertreestar;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class RetryUtilTest {

    /**
     * 重试5次，间隔1000ms
     * 不进行异常处理
     */
    @Test
    public void test(){

        try{
            RetryUtil.invoke(()->{return "watertreestar";},
                    Objects::nonNull,
                    1000,
                    5,
                    null,
                    ()->new RuntimeException());
            Assert.fail();
        }catch (Exception e){

        }

        RetryUtil.invoke(()->{return "watertreestar";},
                Objects::isNull,
                1000,
                5,
                null,
                ()->new RuntimeException());

        try{
            RetryUtil.invoke(null,
                    Objects::isNull,
                    1000,
                    5,
                    null,
                    ()->new RuntimeException());
            Assert.fail();
        }catch (Exception e){

        }

    }

    /**
     * 进行异常处理
     */
    @Test
    public void testExceptionHandle(){
        RetryUtil.invoke(()->{throw new IllegalStateException();},
                Objects::isNull,
                2000,
                5,
                e -> {
                    System.out.println(e);
                    return null;
                },
                ()->new RuntimeException());
    }
}
