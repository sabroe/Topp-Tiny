package com.yelstream.topp.util.function.ex;

import lombok.experimental.UtilityClass;

/**
 * Utility addressing instances of {@link SupplierEx}.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-04-15
 */
@UtilityClass
public class SupplierExs {
    /**
     * Gets the value of a supplier, if set.
     * @param supplier Supplier.
     * @param <T> Type of supplier value.
     * @param <E> Type of error.
     * @return Value of supplier, if set.
     *         If supplier is {@code null} then this is {@code null}.
     * @throws E Thrown in case of error.
     */
    public <T, E extends Throwable> T get(SupplierEx<T, E> supplier) throws E {
        return supplier==null?null:supplier.get();
    }

    /**
     * Creates a safe, non-{@code null} supplier.
     * @param supplier Supplier
     * @param <T> Type of supplier value.
     * @param <E> Type of error.
     * @return Supplier wrapping the argument supplier.
     *         This is non-{@code null} and obtains its value from {@link #get(SupplierEx)} on the argument supplier.
     */
    public <T, E extends Throwable> SupplierEx<T, E> create(SupplierEx<T, E> supplier) {
        return ()->get(supplier);
    }
}
