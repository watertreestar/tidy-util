package com.github.watertreestar;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {
    @Test
    public void testRemoveEnd(){
        String str = "abdcedf!fafaf";
        String result = StringUtil.removeLast(str,"!");
        Assert.assertEquals("abdcedf",result);

        str = "!fafafaf!errererer";
        result = StringUtil.removeLast(str,"!");
        Assert.assertEquals("!fafafaf",result);

        str = "!fafafaferrererer";
        result = StringUtil.removeLast(str,"!");
        Assert.assertEquals("",result);

        str = "fafafaferrererer";
        result = StringUtil.removeLast(str,"!");
        Assert.assertEquals("fafafaferrererer",result);

        str = "fafafaferrer-/erer";
        result = StringUtil.removeLast(str,"-/");
        Assert.assertEquals("fafafaferrer",result);

        str = null;
        result = StringUtil.removeLast(str,"!");
        Assert.assertEquals(null,result);
    }
}
