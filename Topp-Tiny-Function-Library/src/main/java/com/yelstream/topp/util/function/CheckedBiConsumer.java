package com.yelstream.topp.util.function;

import com.yelstream.topp.util.function.ex.BiConsumerWithException;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts two input arguments and returns no
 * result.  This is the two-arity specialization of {@link Consumer}.
 * Unlike most other functional interfaces, {@code BiConsumer} is expected
 * to operate via side effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T> Type of the first argument to the operation
 * @param <U> Type of the second argument to the operation.
 * @param <E> Thrown in case of error.
 *            This is checked.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-04-20
 */
@SuppressWarnings("unused")
public interface CheckedBiConsumer<T, U, E extends Exception> extends BiConsumerWithException<T, U, E> {
}
