package com.github.watertreestar.array;

import java.lang.reflect.Array;

/**
 * util for array
 */
public class ArrayUtil {
    /**
     * 对象是否是数组
     * @param obj
     * @return
     */
    public static boolean isArray(Object obj){
        if (obj == null) return false;
        Class c = obj.getClass();
        return c.isArray();
    }

    /**
     * 对象转数组, 传入的对象必须是数组转成的。
     */
    public static Object[] toArray(Object array) {
        if (!isArray(array)) return null;
        int size = Array.getLength(array);
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = Array.get(array, i);
        }
        return objects;
    }

    /**
     * 数组的{@code toString}方法
     */
    public static String toString(Object array) {
        if (!isArray(array)) {
            throw new IllegalArgumentException("parameter object not array.");
        }
        Object[] array0 = toArray(array);
        String str = "[";
        for (Object o : array0) {
            str += o + ",";
        }
        str = str.substring(0, str.length() - 1) + "]";
        return str;
    }

    /**
     * 数组的{@code toString}方法
     */
    public static String toString(Object array,String delimiter) {
        if (!isArray(array)) {
            throw new IllegalArgumentException("parameter object not array.");
        }
        Object[] array0 = toArray(array);
        String str = "[";
        for (Object o : array0) {
            str += o + delimiter;
        }
        str = str.substring(0, str.length() - 1) + "]";
        return str;
    }
}
