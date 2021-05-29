package com.github.watertreestar;

/**
 * Provides implementation of hashcode for primitive and compound object
 */
public class HashCodeUtil {
    private static final int X = 31;

    public static int hashCode(Object o1) {
        return hashCode(o1 == null ? 0 : o1.hashCode());
    }

    public static int hashCode(Object o1, Object o2) {
        return hashCode(o1 == null ? 0 : o1.hashCode(), o2 == null ? 0 : o2.hashCode());
    }

    public static int hashCode(Object o1, Object o2, Object o3) {
        return hashCode(
                o1 == null ? 0 : o1.hashCode(),
                o2 == null ? 0 : o2.hashCode(),
                o3 == null ? 0 : o3.hashCode());
    }

    public static int hashCode(
            Object o1, Object o2, Object o3, Object o4) {
        return hashCode(
                o1 == null ? 0 : o1.hashCode(),
                o2 == null ? 0 : o2.hashCode(),
                o3 == null ? 0 : o3.hashCode(),
                o4 == null ? 0 : o4.hashCode());
    }

    public static int hashCode(
            Object o1,
            Object o2,
            Object o3,
            Object o4,
            Object o5) {
        return hashCode(
                o1 == null ? 0 : o1.hashCode(),
                o2 == null ? 0 : o2.hashCode(),
                o3 == null ? 0 : o3.hashCode(),
                o4 == null ? 0 : o4.hashCode(),
                o5 == null ? 0 : o5.hashCode());
    }

    public static int hashCode(
            Object o1,
            Object o2,
            Object o3,
            Object o4,
            Object o5,
            Object o6) {
        return hashCode(
                o1 == null ? 0 : o1.hashCode(),
                o2 == null ? 0 : o2.hashCode(),
                o3 == null ? 0 : o3.hashCode(),
                o4 == null ? 0 : o4.hashCode(),
                o5 == null ? 0 : o5.hashCode(),
                o6 == null ? 0 : o6.hashCode());
    }

    public static int hashCode(int i1) {
        int acc = X + i1;
        return acc;
    }

    public static int hashCode(int i1, int i2) {
        int acc = X + i1;
        acc = X * acc + i2;
        return acc;
    }

    public static int hashCode(int i1, int i2, int i3) {
        int acc = X + i1;
        acc = X * acc + i2;
        acc = X * acc + i3;
        return acc;
    }

    public static int hashCode(int i1, int i2, int i3, int i4) {
        int acc = X + i1;
        acc = X * acc + i2;
        acc = X * acc + i3;
        acc = X * acc + i4;
        return acc;
    }

    public static int hashCode(int i1, int i2, int i3, int i4, int i5) {
        int acc = X + i1;
        acc = X * acc + i2;
        acc = X * acc + i3;
        acc = X * acc + i4;
        acc = X * acc + i5;
        return acc;
    }

    public static int hashCode(int i1, int i2, int i3, int i4, int i5, int i6) {
        int acc = X + i1;
        acc = X * acc + i2;
        acc = X * acc + i3;
        acc = X * acc + i4;
        acc = X * acc + i5;
        acc = X * acc + i6;
        return acc;
    }
}
