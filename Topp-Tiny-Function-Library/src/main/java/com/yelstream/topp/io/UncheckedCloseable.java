package com.yelstream.topp.io;

import com.yelstream.topp.util.lang.UncheckedAutoCloseable;

/**
 * Closeable with no checked exception thrown in the signature of {@link #close()}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-18
 */
@FunctionalInterface
public interface UncheckedCloseable extends UncheckedAutoCloseable {
    @Override
    void close();
}
