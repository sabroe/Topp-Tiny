package com.yelstream.topp.util.uuid;

import java.util.function.Supplier;

/**
 * Supplier of UUID factories.
 */
@FunctionalInterface
public interface UUIDFactorySupplier {
    UUIDFactory createUUIDFactory();

    static UUIDFactorySupplier of(Supplier<UUIDFactory> supplier) {
        return supplier::get;
    }
}