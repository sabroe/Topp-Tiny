package com.yelstream.topp.util.collection;

import lombok.Data;

/**
 * Holder of an object.
 * @param <E> Type of object held.
 */
@Data
public class Reference<E> {
    /**
     * Held object.
     */
    private E element;
}
