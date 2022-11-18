package com.yelstream.topp.lang;

/**
 * Auto-closeable with no checked exception thrown in the signature of {@link #close()}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-18
 */
@FunctionalInterface
public interface UncheckedAutoCloseable extends AutoCloseable {
    @Override
    void close();
}
