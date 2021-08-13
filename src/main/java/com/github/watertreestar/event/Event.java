package com.github.watertreestar.event;

/**
 * Root class for all event holds a event source
 */
public class Event {
    protected transient Object source;

    private long timestamp;

    public Event(Object source) {
        if (source == null)
            throw new IllegalArgumentException("null source");

        this.source = source;
        this.timestamp = System.currentTimeMillis();
    }

    public Object getSource() {
        return source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}
