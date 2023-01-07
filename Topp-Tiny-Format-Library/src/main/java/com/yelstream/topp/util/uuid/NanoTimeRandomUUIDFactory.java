package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * .
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
public class NanoTimeRandomUUIDFactory implements UUIDFactory {
    @Override
    public final UUID createUUID() {
        long msb=System.currentTimeMillis();
        long lsb=System.nanoTime();
        return UUIDs.createUUIDVersion4MSBLSBLeftShift(msb,lsb);
    }
}
