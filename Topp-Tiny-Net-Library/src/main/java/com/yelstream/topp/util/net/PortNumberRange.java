package com.yelstream.topp.util.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Port number range.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2023-04-01
 */
@Getter
@AllArgsConstructor(staticName="of")
public class PortNumberRange {
    /**
     * Start of port number range.
     * This is inclusive.
     */
    private final int beginRange;

    /**
     * End of port number range.
     * This is exclusive.
     */
    private final int endRange;
}
