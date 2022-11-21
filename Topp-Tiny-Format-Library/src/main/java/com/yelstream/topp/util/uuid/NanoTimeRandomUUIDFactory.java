package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class NanoTimeRandomUUIDFactory implements UUIDFactory {
    @Override
    public final UUID createUUID() {
        long msb=System.currentTimeMillis();
        long lsb=System.nanoTime();
        return UUIDs.createUUIDVersion4MSBLSBLeftShift(msb,lsb);
    }
}
