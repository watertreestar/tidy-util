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

    @Test
    public void testPkgToPath(){
        String str = "com.github.watertreestar";
        String result = StringUtil.packageToPath(str);
        Assert.assertEquals("com/github/watertreestar",result);
    }

    @Test
    public void testPathToPkg(){
        String str = "com/github/watertreestar";
        String result = StringUtil.pathToPackage(str);
        Assert.assertEquals("com.github.watertreestar",result);
    }
}
