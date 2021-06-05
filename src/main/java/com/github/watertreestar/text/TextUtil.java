package com.github.watertreestar.text;

import com.github.watertreestar.text.parse.TextParser;

/**
 * Provides utility to transform text
 */
public class TextUtil {
    public static String format(String pattern, Object... values) {
        return TextParser.parse1(pattern, values);
    }
}