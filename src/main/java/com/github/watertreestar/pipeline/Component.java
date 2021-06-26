package com.github.watertreestar.pipeline;

import java.util.Collection;

public interface Component<T> extends LifeCycle {
    /**
     * 组件名称
     *
     * @return
     */
    String getName();

    /**
     * 获取下游组件
     *
     * @return
     */
    Collection<Component> getDownStreams();

    /**
     * 执行
     */
    void execute(T o);
}
