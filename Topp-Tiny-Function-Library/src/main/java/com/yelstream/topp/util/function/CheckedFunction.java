package com.yelstream.topp.util.function;

import com.yelstream.topp.util.function.ex.FunctionWithException;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> The type of the input to the function.
 * @param <R> The type of the result of the function.
 * @param <E> Thrown in case of error.
 *            This is checked.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-04-20
 */
@SuppressWarnings("unused")
public interface CheckedFunction<T, R, E extends Exception> extends FunctionWithException<T, R, E> {
}
