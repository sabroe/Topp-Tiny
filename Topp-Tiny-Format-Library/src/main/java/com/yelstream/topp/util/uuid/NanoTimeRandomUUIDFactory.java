package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class NanoTimeRandomUUIDFactory extends AbstractRandomUUIDFactory {
    public NanoTimeRandomUUIDFactory() {
    }
  
    @Override
    public final UUID createUUID() {
        long msb=System.currentTimeMillis();
        long lsb=System.nanoTime();
        return createUUIDVersion4_MSB_LSB_LeftShift(msb,lsb);
    }
}
