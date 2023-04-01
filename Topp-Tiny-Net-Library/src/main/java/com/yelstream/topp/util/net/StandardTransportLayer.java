package com.yelstream.topp.util.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Standard transport layers within the internet protocol suite.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2023-04-01
 */
@Getter
@AllArgsConstructor
public enum StandardTransportLayer {
    /**
     * Transmission Control Protocol (TCP).
     */
    TCP(TransportLayer.of("TCP","Transmission Control Protocol")),

    /**
     * User Datagram Protocol (UDP).
     */
    UDP(TransportLayer.of("UDP","User Datagram Protocol")),

    /**
     * Stream Control Transport Protocol (SCTP).
     */
    SCTP(TransportLayer.of("SCTP","Stream Control Transport Protocol"));

    //DCCP(TransportLayer.of("DCCP","Datagram Congestion Control Protocol",TransportLayer.Purpose.Transport)),
    //RSVP(TransportLayer.of("RSVP","Resource Reservation Protocol",TransportLayer.Purpose.Signalling)),
    //QUIC(TransportLayer.of("QUIC","QUIC",TransportLayer.Purpose.Transport));

    /**
     * Transport layer.
     */
    private final TransportLayer transportLayer;
}
