package com.yelstream.topp.util.function.access;

import java.util.function.BiConsumer;

/**
 * Property setter operating on an object.
 * @param <O> Type of object.
 * @param <V> Type of value.
 */
@FunctionalInterface
public interface Setter<O,V> {
    /**
     * Sets property value.
     * @param object Object.
     * @param value Value.
     */
    void set(O object,
             V value);

    /**
     * Creates a property setter.
     * @param writer Writer of property.
     * @return Property setter
     * @param <O> Type of object.
     * @param <V> Type of value.
     */
    static <O,V> Setter<O,V> of(BiConsumer<O,V> writer) {
        return writer::accept;
    }
}
