package com.github.watertreestar.text;

import com.github.watertreestar.text.parse.TextParser;
import org.junit.Test;

public class TextParserTest {
    @Test
    public void test(){
        System.out.println(TextParser.parse("{", "}", "我的名字是\\{},结果是{}，可信度是%{}", "雷锋", true, 100));
    }

    @Test
    public void test0(){
        System.out.println(TextParser.parse0("我的名字是${},结果是${}，可信度是%${}", "雷锋", true, 100));

    }

    @Test
    public void test1(){
        System.out.println(TextParser.parse1("我的名字是{},结果是{}，可信度是%{}", "雷锋", true, 100));
    }
}
