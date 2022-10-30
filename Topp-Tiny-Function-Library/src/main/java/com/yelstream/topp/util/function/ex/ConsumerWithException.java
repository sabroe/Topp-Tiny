package com.yelstream.topp.util.function.ex;

import java.util.Objects;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object)}.
 *
 * @param <T> the type of the input to the operation
 * @param <E> Thrown in case of error.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-04-15
 */
@FunctionalInterface
@SuppressWarnings("unused")
public interface ConsumerWithException<T, E extends Throwable> {
    /**
     * Performs this operation on the given argument.
     * @param t the input argument
     * @throws E Thrown in case of error.
     */
    void accept(T t) throws E;

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default ConsumerWithException<T, E> andThen(ConsumerWithException<? super T, ? extends E> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
