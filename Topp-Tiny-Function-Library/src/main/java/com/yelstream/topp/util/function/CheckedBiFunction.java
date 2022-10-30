package com.yelstream.topp.util.function;

import com.yelstream.topp.util.function.ex.BiFunctionWithException;

import java.util.function.Function;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the two-arity specialization of {@link Function}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> Type of the first argument to the function.
 * @param <U> Type of the second argument to the function.
 * @param <R> Type of the result of the function.
 * @param <E> Thrown in case of error.
 *            This is checked.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-04-20
 */
@SuppressWarnings("unused")
public interface CheckedBiFunction<T, U, R, E extends Exception> extends BiFunctionWithException<T, U, R, E> {
}
