package com.yelstream.topp.util.event;

import java.io.IOException;

/**
 * Publisher of events.
 * <p>
 * All semantics depends upon the context.
 * </p>
 * @param <E> Type of events.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2023-09-18
 */
public interface Publisher<E> {
    /**
     * Publishes an event.
     * @param event Event.
     * @throws IOException Thrown in case of I/O error.
     */
    void publish(E event) throws IOException;
}
