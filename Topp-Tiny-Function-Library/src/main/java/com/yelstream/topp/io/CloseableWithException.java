package com.yelstream.topp.io;

import com.yelstream.topp.util.lang.AutoCloseableWithException;

import java.io.IOException;

/**
 * Closeable with a specific I/O-exception in the signature of {@link #close()}.
 * @param <E> Thrown in case {@link #close()} fails.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-18
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CloseableWithException<E extends IOException> extends AutoCloseableWithException<E> {
    @Override
    void close() throws E;
}
