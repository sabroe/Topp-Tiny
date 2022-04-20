package com.yelstream.topp.util.function.ex;

import java.util.Objects;
import java.util.function.Function;

/**
csssssssssssssssssc * @see Function
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-04-15
 */
@FunctionalInterface
public interface BiFunctionWithException<T, U, R, E extends Throwable> {
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     * @throws E Thrown in case of error.
     */
    R apply(T t, U u) throws E;

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> BiFunctionWithException<T, U, V, E> andThen(FunctionWithException<? super R, ? extends V, ? extends E> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }
}