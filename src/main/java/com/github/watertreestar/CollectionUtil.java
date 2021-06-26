package com.github.watertreestar;

import java.util.*;

/**
 * Provides a serial of util to operate or return collection
 */
public class CollectionUtil {
    public static Collection listOf(Object... pieces) {
        return Arrays.asList(pieces);
    }

    /**
     * Return a list copy
     * @param from
     * @param <E>
     * @return
     */
    public static <E> List<E> copyOf(List<E> from) {
        List<E> dest = new ArrayList<>();
        Collections.copy(dest, from);
        return dest;
    }

    /**
     * Determine whether the collection is empty
     *
     * @param c
     * @return
     */
    public static boolean isEmpty(Collection<?> c) {
        if (c == null) return true;
        return c.size() < 1;
    }
}
