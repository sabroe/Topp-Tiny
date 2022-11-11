package com.yelstream.topp.util.function.access;

import java.util.function.BiConsumer;

/**
 * Property setter operating on an object.
 * @param <O> Type of object.
 * @param <V> Type of value.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-05-14
 */
@SuppressWarnings("unused")
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
     * @param <O> Type of object.
     * @param <V> Type of value.
     * @return Property setter
     */
    static <O,V> Setter<O,V> of(BiConsumer<O,V> writer) {
        return writer::accept;
    }
}
