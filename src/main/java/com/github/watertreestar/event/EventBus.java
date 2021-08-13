package com.github.watertreestar.event;

/**
 * Provide event publish and registry
 */
public interface EventBus {
    /**
     * Register a subscriber for listeners
     *
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * Unregister subscriber
     *
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * Publish a event to trigger listeners
     *
     * @param event
     */
    void publish(Event event);
}
