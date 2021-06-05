package com.github.watertreestar.text;

import org.junit.Assert;
import org.junit.Test;

public class TextUtilTest {
    @Test
    public void test() {
        String result = TextUtil.format("{},hello.{} is a good program language", "watertreestar", "Java");
        Assert.assertNotNull(result);
        Assert.assertEquals("watertreestar,hello.Java is a good program language", result);
    }
}
