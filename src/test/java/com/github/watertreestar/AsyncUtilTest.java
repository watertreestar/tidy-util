package com.github.watertreestar;

import com.github.watertreestar.functional.async.AsyncUtil;
import org.junit.Test;

public class AsyncUtilTest {

    @Test
    public void test(){
        AsyncUtil.doAsync(()->{
            System.out.println("do something before task");
            return null;
        },()->{
            System.out.println("do task");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task result";
        },(r)-> {
            System.out.println(r);
        });

        System.out.println("do something in main");

        AsyncUtil.doAsync(()->{
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("do something before task1");
            return null;
        },()->{
            System.out.println("do task1");
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 result";
        },(r)-> {
            System.out.println(r);
        });

        System.out.println("do task2 in main");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
