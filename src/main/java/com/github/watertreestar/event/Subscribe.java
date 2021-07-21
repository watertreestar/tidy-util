package com.github.watertreestar.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a method is an event consumer.  The method must have a single argument and the argument's type determines
 * what type of events should be delivered to the method for consumption.
 * <p>
 * <p/>
 * For example:
 * <pre>
 * &#64;Subscribe
 * public void onSomeEvent(SomeEvent event) { ... }
 * </pre>
 * <p/>
 * Because the method argument is declared as a {@code SomeEvent} type, the method will be called by the event
 * dispatcher whenever a {@code SomeEvent} instance (or one of its subclass instances that is not already registered)
 * is published.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Subscribe {
}
