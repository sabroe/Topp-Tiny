package com.yelstream.topp.util.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Holder of an object.
 * @param <E> Type of object held.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-05-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reference<E> {
    /**
     * Held object.
     */
    private E element;
}
