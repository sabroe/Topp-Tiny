package com.yelstream.topp.util.uuid;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class CountTimeRandomUUIDFactory extends AbstractRandomUUIDFactory {
    public CountTimeRandomUUIDFactory() {
    }

    protected AtomicLong counter=new AtomicLong();
  
    @Override
    public final UUID createUUID() {
        long msb=System.currentTimeMillis();
        long lsb=counter.getAndIncrement();
        return createUUIDVersion4_MSB_LSB_LeftShift(msb,lsb);
    }
}
