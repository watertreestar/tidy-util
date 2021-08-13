package com.github.watertreestar;

import org.junit.Test;

public class PlaceholderResolverTest {
    @Test
    public void testResolve() {
        String content = PlaceholderResolver.getDefaultResolver().resolve("hello,${name}, i'm \\${love", "ranger", "ww");
        System.out.println(content);
    }
}
