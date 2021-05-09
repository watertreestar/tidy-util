package com.github.watertreestar;

import java.io.File;

public class StringUtil {
    public static String packageToPath(String pkgName) {
        return pkgName.replaceAll("\\.", File.separator);
    }

    public static String pathToPackage(String pathName) {
        if (pathName.startsWith("/")) {
            pathName = pathName.substring(1);
        }
        return pathName.replaceAll("/", ".");
    }

    public static String concat(Object... objs) {
        StringBuilder sb = new StringBuilder(30);
        for (int ix = 0; ix < objs.length; ++ix) {
            sb.append(objs[ix]);
        }
        return sb.toString();
    }

    public static String trimSuffix(String name) {
        int dotIndex = name.indexOf('.');
        if (-1 == dotIndex) {
            return name;
        }
        return name.substring(0, dotIndex);
    }
}
