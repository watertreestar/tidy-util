package com.github.watertreestar;

import java.io.File;

public class StringUtil {
    public static final String DOT_CHAR = ".";
    public static final String COLON_CHAR = ":";
    public static final String EXCLAMATION_CHAR = "!";
    public static final String SLASH_CHAR = "/";
    public static final String ESCAPE_CHAR = "\\";
    public static final String DOUBLE_SLASH_CHAR = "//";


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

    public static String extractPathFromJar(String jarURLPath){
        if(jarURLPath == null) {
            return null;
        }
        int start = jarURLPath.indexOf(COLON_CHAR);
        int end = jarURLPath.lastIndexOf(EXCLAMATION_CHAR);
        return jarURLPath.substring(start + 1,end);
    }
}
