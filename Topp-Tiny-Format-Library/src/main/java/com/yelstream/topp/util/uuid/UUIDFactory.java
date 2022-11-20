package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * Factory of universally unique identifiers.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@FunctionalInterface
public interface UUIDFactory {
    /**
     * Creates a new UUID.
     * @return Randomly generated UUID.
     */
    UUID createUUID();
}
