package com.yelstream.topp.util.function;

import com.yelstream.topp.util.function.ex.SupplierWithException;

/**
 * Represents a supplier of results.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()}.
 *
 * @param <T> The type of results supplied by this supplier.
 * @param <E> Thrown in case of error.
 *            This is checked.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-04-20
 */
@SuppressWarnings("unused")
public interface CheckedSupplier<T, E extends Exception> extends SupplierWithException<T, E> {
}
