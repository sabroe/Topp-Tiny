package com.yelstream.topp.util.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Standard port number range.
 * Equivalent to port number type.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2023-04-01
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("java:S115")
public enum StandardPortNumberRange {
    /**
     * Well-known ports.
     */
    WellKnown(PortNumberRange.of(0,1024)),

    /**
     * Registered ports.
     */
    Registered(PortNumberRange.of(1024,49152)),

    /**
     * Dynamic and/or private ports.
     */
    DynamicOrPrivate(PortNumberRange.of(49152,65536));

    /**
     * Port number range.
     */
    private final PortNumberRange range;
}
