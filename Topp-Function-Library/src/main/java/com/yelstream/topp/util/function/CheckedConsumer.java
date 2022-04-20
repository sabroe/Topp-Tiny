package com.yelstream.topp.util.function;

import com.yelstream.topp.util.function.ex.ConsumerWithException;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object)}.
 *
 * @param <T> Type of the input to the operation.
 * @param <E> Thrown in case of error.
 *            This is checked.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-04-20
 */
public interface CheckedConsumer<T, E extends Exception> extends ConsumerWithException<T, E> {
}
