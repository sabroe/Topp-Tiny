package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.RandomDataFactory;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class ConcurrentRandomUUIDFactory extends AbstractRandomUUIDFactory {
    public ConcurrentRandomUUIDFactory(Integer capacity, Boolean fair) {
        randomDataFactory=new ConcurrentRandomDataFactory(capacity,fair);
    }
  
    public ConcurrentRandomUUIDFactory(Integer capacity) {
        this(capacity,null);
    }
  
    public ConcurrentRandomUUIDFactory() {
        this(null,null);
    }
  
    private final RandomDataFactory randomDataFactory;

    @Override
    public final UUID createUUID() {
        byte[] data=new byte[16];
        randomDataFactory.nextBytes(data);
        return createUUIDVersion4(data);
    }
}
