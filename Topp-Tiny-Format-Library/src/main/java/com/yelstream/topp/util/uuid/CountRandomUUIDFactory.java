package com.yelstream.topp.util.uuid;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class CountRandomUUIDFactory implements UUIDFactory {
    public CountRandomUUIDFactory() {
        countHolder.set(BigInteger.ZERO);
    }

    private final AtomicReference<BigInteger> countHolder=new AtomicReference<>();
  
    protected BigInteger incrementAndGet() {
        while (true) {
            BigInteger current=countHolder.get();
            BigInteger next=current.add(BigInteger.ONE);
            if (countHolder.compareAndSet(current,next)) {
                return next;
            }
        }
    }
  
    @Override
    public final UUID createUUID() {
        BigInteger b=incrementAndGet();
        long msb=b.shiftRight(64).longValue();
        long lsb=b.longValue();
        return UUIDs.createUUIDVersion4MSBLSBLeftShift(msb,lsb);
    }
}
