package com.github.watertreestar;

import java.util.List;

/**
 * Utils for Predication
 */
public class Predications {

    public static void checkIndex(List<?> list, int index) {
        int size = list.size();
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid List index, List size:" + size + ", index:" + index);
        }
    }

    public static void checkArrayIndex(byte[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(short[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(int[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(long[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(float[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(double[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(boolean[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(char[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayIndex(Object[] array, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("invalid array index, array size:" + array.length + ", index:" + index);
        }
    }

    public static void checkArrayRange(byte[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(short[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(int[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(long[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(float[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(double[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(boolean[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(char[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }

    public static void checkArrayRange(Object[] array, int off, int len) {
        if (off < 0 || len < 0 || off + len > array.length) {
            throw new IllegalArgumentException("invalid array range, array size:" + array.length + ", offset:" + off + ", len:" + len);
        }
    }
}
