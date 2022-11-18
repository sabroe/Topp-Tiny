package com.yelstream.topp.lang;

/**
 * Auto-closeable with a specific exception in the signature of {@link #close()}.
 * @param <E> Thrown in case {@link #close()} fails.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-18
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface AutoCloseableWithException<E extends Exception> extends AutoCloseable {
    @Override
    void close() throws E;
}
