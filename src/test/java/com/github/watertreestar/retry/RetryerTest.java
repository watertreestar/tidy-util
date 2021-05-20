package com.github.watertreestar.retry;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RetryerTest {
    @Test
    public void testNormal() {
        final List<String> list = new ArrayList<String>();
        Retryer.retry(3, new Runnable() {
            @Override
            public void run() {
                list.add("watertreestar");
            }
        });
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("watertreestar"));
    }

    @Test
    public void testException() throws Throwable {
        try {
            Retryer.retry(3, new Runnable() {
                @Override
                public void run() throws Throwable {
                    throw new IOException();
                }
            });
            Assert.fail();
        } catch (Throwable t) {

        }

    }


}
