package com.github.watertreestar.pipeline;

public interface LifeCycle {
    /**
     * 初始化
     *
     * @param config
     */
    void init(String config);

    /**
     * 启动
     */
    void startup();

    /**
     * 结束
     */
    void shutdown();
}
