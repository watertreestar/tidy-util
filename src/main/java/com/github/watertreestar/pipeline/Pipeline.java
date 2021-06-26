package com.github.watertreestar.pipeline;

public class Pipeline implements LifeCycle {
    private Component first;

    @Override
    public void init(String config) {
        first.init(config);
    }

    @Override
    public void startup() {
        first.startup();
    }

    @Override
    public void shutdown() {
        first.shutdown();
    }
}
