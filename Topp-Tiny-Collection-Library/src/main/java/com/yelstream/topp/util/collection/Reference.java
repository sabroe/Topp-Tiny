package com.yelstream.topp.util.collection;

import lombok.Data;

/**
 * Holder of an object.
 * @param <E> Type of object held.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-05-14
 */
@Data
public class Reference<E> {
    /**
     * Held object.
     */
    private E element;
}
