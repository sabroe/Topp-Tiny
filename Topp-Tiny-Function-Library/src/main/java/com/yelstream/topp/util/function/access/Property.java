package com.yelstream.topp.util.function.access;

import lombok.AllArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Property operating on an object.
 * @param <O> Type of object.
 * @param <V> Type of value.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-05-14
 */
@SuppressWarnings("unused")
@AllArgsConstructor(staticName="of")
public class Property<O,V> implements Getter<O,V>, Setter<O,V> {
    /**
     * Property getter.
     */
    private final Getter<O,V> getter;

    /**
     * Property setter.
     */
    private final Setter<O,V> setter;

    /**
     * Gets property value.
     * @param object Object.
     * @return Value.
     */
    @Override
    public V get(O object) {
        return getter.get(object);
    }

    /**
     * Sets property value.
     * @param object Object.
     * @param value Value.
     */
    @Override
    public void set(O object,
                    V value) {
        setter.set(object,value);
    }

    /**
     * Creates a property.
     * @param reader Reader of property.
     * @param writer Writer of property.
     * @return Property.
     * @param <O> Type of object.
     * @param <V> Type of value.
     */
    static <O,V> Property<O,V> of(Function<O,V> reader,
                                  BiConsumer<O,V> writer) {
        return of(Getter.of(reader),Setter.of(writer));
    }
}
