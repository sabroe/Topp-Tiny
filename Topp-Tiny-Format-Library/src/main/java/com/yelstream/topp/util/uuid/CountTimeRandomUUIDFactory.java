package com.yelstream.topp.util.uuid;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class CountTimeRandomUUIDFactory implements UUIDFactory {
    protected final AtomicLong counter=new AtomicLong();
  
    @Override
    public final UUID createUUID() {
        long msb=System.currentTimeMillis();
        long lsb=counter.getAndIncrement();
        return UUIDs.createUUIDVersion4MSBLSBLeftShift(msb,lsb);
    }
}
