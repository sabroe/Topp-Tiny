package com.yelstream.topp.util.net;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Transport layer within the internet protocol suite.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2023-04-01
 */
@Getter
@AllArgsConstructor(staticName="of")
@Builder(builderClassName="Builder",toBuilder=true)
public class TransportLayer {
    /*
    enum Purpose {
        Transport,
        Signalling
    }
    */

    private final String acronym;

    private final String name;

    //private final Purpose purpose;
    //private final boolean nativeLayer;
    //private final TransportLayer carrier;
}
