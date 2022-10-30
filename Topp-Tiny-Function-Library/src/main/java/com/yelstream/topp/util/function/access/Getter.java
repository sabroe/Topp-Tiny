package com.yelstream.topp.util.function.access;

import java.util.function.Function;

/**
 * Property getter operating on an object.
 * @param <O> Type of object.
 * @param <V> Type of value.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-05-14
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Getter<O,V> {
    /**
     * Gets property value.
     * @param object Object.
     * @return Value.
     */
    V get(O object);

    /**
     * Creates a property getter.
     * @param reader Reader of property.
     * @return Property getter
     * @param <O> Type of object.
     * @param <V> Type of value.
     */
    static <O,V> Getter<O,V> of(Function<O,V> reader) {
        return reader::apply;
    }
}
