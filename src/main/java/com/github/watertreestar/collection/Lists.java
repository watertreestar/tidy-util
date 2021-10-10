package com.github.watertreestar.collection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Lists {
    private static final int INIT_SIZE = 16;

    /**
     * If list is null, return immutable empty list; else return list self.
     *
     * @param list the list
     * @param <T>  the element type
     * @return non-null list
     */
    public static <T> List<T> nullToEmpty(List<T> list) {
        if (list == null) {
            return Lists.of();
        }
        return list;
    }

    /**
     * Create new empty ArrayList
     */
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * Create new ArrayList.
     */
    public static <T> ArrayList<T> newArrayList(T v) {
        ArrayList<T> list = new ArrayList<>();
        list.add(v);
        return list;
    }

    /**
     * Create new ArrayList.
     */
    public static <T> ArrayList<T> newArrayList(T v1, T v2) {
        ArrayList<T> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        return list;
    }

    /**
     * Create new ArrayList.
     */
    public static <T> ArrayList<T> newArrayList(T v1, T v2, T v3) {
        ArrayList<T> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        return list;
    }

    /**
     * Create new ArrayList.
     */
    public static <T> ArrayList<T> newArrayList(T v1, T v2, T v3, T v4) {
        ArrayList<T> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        return list;
    }

    /**
     * Create new ArrayList.
     */
    public static <T> ArrayList<T> newArrayList(T v1, T v2, T v3, T v4, T v5) {
        ArrayList<T> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        return list;
    }

    /**
     * Create new array List.
     */
    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... values) {
        return newList(ArrayList::new, values);
    }

    /**
     * For easy list creation with initial values.
     *
     * @param supplier the list supplier
     * @param values   the elements to add into list
     * @param <T>      element type
     * @return the list
     */
    @SafeVarargs
    public static <T, R extends List<T>> R newList(Supplier<R> supplier, T... values) {
        R list = supplier.get();
        Collections.addAll(list, values);
        return list;
    }

    /**
     * Create new immutable empty List
     */
    public static <T> List<T> of() {
        return Collections.emptyList();
    }

    /**
     * Create new immutable List.
     */
    public static <T> List<T> of(T v) {
        return Collections.singletonList(v);
    }

    /**
     * Create new immutable List.
     */
    public static <T> List<T> of(T v1, T v2) {
        return Collections.unmodifiableList(newArrayList(v1, v2));
    }

    /**
     * Create new immutable List.
     */
    public static <T> List<T> of(T v1, T v2, T v3) {
        return Collections.unmodifiableList(newArrayList(v1, v2, v3));
    }

    /**
     * Create new immutable List.
     */
    public static <T> List<T> of(T v1, T v2, T v3, T v4) {
        return Collections.unmodifiableList(newArrayList(v1, v2, v3, v4));
    }

    /**
     * Create new immutable List.
     */
    public static <T> List<T> of(T v1, T v2, T v3, T v4, T v5) {
        return Collections.unmodifiableList(newArrayList(v1, v2, v3, v4, v5));
    }

    /**
     * Create new immutable List.
     */
    public static <T> List<T> of(T v1, T v2, T v3, T v4, T v5, T v6) {
        return Collections.unmodifiableList(newArrayList(v1, v2, v3, v4, v5, v6));
    }

    /**
     * Create new immutable List.
     */
    @SafeVarargs
    public static <T> List<T> of(T... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }

    /**
     * Return a new immutable list equals the origin list
     *
     * @param list cannot be null
     */
    public static <T> List<T> copy(List<T> list) {
        return copyOf(list);
    }

    /**
     * Return a new immutable list equals the origin list
     *
     * @param list cannot be null
     */
    public static <T> List<T> copyOf(List<T> list) {
        if (list.isEmpty()) {
            return Lists.of();
        }
        return Collections.unmodifiableList(new ArrayList<>(list));
    }
}
