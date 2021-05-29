package com.github.watertreestar;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringUtil {
    public static final String DOT_CHAR = ".";
    public static final String COLON_CHAR = ":";
    public static final String EXCLAMATION_CHAR = "!";
    public static final String SLASH_CHAR = "/";
    public static final String ESCAPE_CHAR = "\\";
    public static final String DOUBLE_SLASH_CHAR = "//";
    public static final String EMPTY_STRING = "";


    public static String packageToPath(String pkgName) {
        return pkgName.replaceAll("\\.", File.separator);
    }

    public static String pathToPackage(String pathName) {
        if (pathName.startsWith(SLASH_CHAR)) {
            pathName = pathName.substring(1);
        }
        return pathName.replaceAll(SLASH_CHAR, ".");
    }

    public static String concat(Object... objs) {
        StringBuilder sb = new StringBuilder(30);
        for (int ix = 0; ix < objs.length; ++ix) {
            sb.append(objs[ix]);
        }
        return sb.toString();
    }

    public static String trimSuffix(String name) {
        int dotIndex = name.indexOf(DOT_CHAR);
        if (-1 == dotIndex) {
            return name;
        }
        return name.substring(0, dotIndex);
    }

    public static String removeLast(String str, String pattern){
        if(str == null){
            return null;
        }
        int index = str.lastIndexOf(pattern);
        if(index > -1){
            return str.substring(0,index);
        }
        return str;
    }

    public static String extractPathFromJarEntry(String jarURLEntryPath) {
        if (jarURLEntryPath == null) {
            return null;
        }
        int start = jarURLEntryPath.indexOf(COLON_CHAR);
        int end = jarURLEntryPath.lastIndexOf(EXCLAMATION_CHAR);
        return jarURLEntryPath.substring(start + 1, end);
    }

    public static boolean isBlank(String str) {
        if (str == null) return true;
        if (str.trim().equals(EMPTY_STRING)) {
            return true;
        }
        return false;
    }

    public static boolean anyBlank(String... pieces) {
        for (String str : pieces) {
            if (isBlank(str)) return true;
        }
        return false;
    }

    public static String join(String separator, String[] pieces) {
        return join(Arrays.asList(pieces), separator);
    }

    public static String join(String[] pieces, String separator) {
        return join(Arrays.asList(pieces), separator);
    }

    public static String join(String separator, List<String> pieces) {
        return join(pieces, separator);
    }

    public static String join(List<String> pieces, String separator) {
        StringBuilder buffer = new StringBuilder();
        Iterator iter = pieces.iterator();

        while (iter.hasNext()) {
            buffer.append((String) iter.next());
            if (iter.hasNext()) {
                buffer.append(separator);
            }
        }

        return buffer.toString();
    }

    public static boolean isEqual(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    public static String repeat(char ch, int count) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < count; ++i)
            buffer.append(ch);

        return buffer.toString();
    }
}
