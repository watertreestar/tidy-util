package com.github.watertreestar.pipeline;

import com.github.watertreestar.CollectionUtil;

import java.util.Collection;

public abstract class AbstractComponent<T, R> implements Component<T> {
    @Override
    public void execute(T o) {
        R r = doExecute(o);
        System.out.println(getName() + " receive " + o + " return " + r);
        Collection<Component> downStreams = getDownStreams();
        if (!CollectionUtil.isEmpty(downStreams)) {
            downStreams.forEach(c -> c.execute(r));
        }
    }

    /**
     * 具体组件执行处理
     *
     * @param o 传入的数据
     * @return
     */
    protected abstract R doExecute(T o);

    @Override
    public void startup() {

        Collection<Component> downStreams = getDownStreams();
        if (!CollectionUtil.isEmpty(downStreams)) {
            downStreams.forEach(Component::startup);
        }

        System.out.println("--------- " + getName() + " is start --------- ");
    }

    @Override
    public void shutdown() {
        System.out.println("--------- " + getName() + " is shutdown --------- ");
        Collection<Component> downStreams = getDownStreams();
        if (!CollectionUtil.isEmpty(downStreams)) {
            downStreams.forEach(Component::shutdown);
        }
    }
}