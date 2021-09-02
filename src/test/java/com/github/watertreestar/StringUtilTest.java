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

    @Test
    public void testConcat(){
        String s1 = "hello,";
        String s2 = "watertreestar";

        String result = StringUtil.concat(s1,s2);
        Assert.assertEquals("hello,watertreestar",result);
    }

    @Test
    public void testTrimSuffix(){
        String str = "fajljljfa.xml";
        String result = StringUtil.trimSuffix(str);
        Assert.assertEquals("fajljljfa",result);

        str = "fafafafafa";
        result = StringUtil.trimSuffix(str);
        Assert.assertEquals("fafafafafa", result);
    }

    @Test
    public void testExtractPathFromJar() {
        String jarPath = "file:/Users/young/.m2/repository/junit/junit/4.12/junit-4.12.jar!/org/junit";
        String result = StringUtil.extractPathFromJarEntry(jarPath);
        Assert.assertEquals("/Users/young/.m2/repository/junit/junit/4.12/junit-4.12.jar", result);
    }

    @Test
    public void testIsBlank() {
        boolean result = StringUtil.isBlank("");
        Assert.assertTrue(result);
        result = StringUtil.isBlank("   ");
        Assert.assertTrue(result);
        result = StringUtil.isBlank(" \n  ");
        Assert.assertTrue(result);
        result = StringUtil.isBlank("  .... ");
        Assert.assertFalse(result);
    }
}
