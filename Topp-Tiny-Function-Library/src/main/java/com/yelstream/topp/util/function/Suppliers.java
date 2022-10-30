package com.yelstream.topp.util.function;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

/**
 * Utility addressing instances of {@link Supplier}.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-04-15
 */
@UtilityClass
@SuppressWarnings("unused")
public class Suppliers {
    /**
     * Gets the value of a supplier, if set.
     * @param supplier Supplier.
     * @param <T> Type of supplier value.
     * @return Value of supplier, if set.
     *         If supplier is {@code null} then this is {@code null}.
     */
    public <T> T get(Supplier<T> supplier) {
        return supplier==null?null:supplier.get();
    }

    /**
     * Creates a safe, non-{@code null} supplier.
     * @param supplier Supplier
     * @param <T> Type of supplier value.
     * @return Supplier wrapping the argument supplier.
     *         This is non-{@code null} and obtains its value from {@link #get(Supplier)} on the argument supplier.
     */
    public <T> Supplier<T> create(Supplier<T> supplier) {
        return ()->get(supplier);
    }
}
