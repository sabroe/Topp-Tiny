package com.yelstream.topp.util.event;

import java.io.IOException;

/**
 * Subscriber of events.
 * <p>
 * All semantics depends upon the context.
 * </p>
 * @param <E> Type of events.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2023-09-18
 */
public interface Subscriber<E> {
    /**
     * Receiver of events.
     * @param <E> Type of events.
     */
    interface Receiver<E> {
        /**
         * Receive event.
         * @param event event
         * @throws IOException Thrown in case of I/O error.
         */
        void receive(E event) throws IOException;
    }

    /**
     * Subscribes to events.
     * @param receiver Receiver of events.
     * @throws IOException Thrown in case of I/O error.
     */
    void subscribe(Receiver<E> receiver) throws IOException;
}
