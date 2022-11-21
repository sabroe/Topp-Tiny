package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.RandomDataFactory;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class ComposedRandomUUIDFactory implements UUIDFactory {
    public ComposedRandomUUIDFactory(RandomDataFactory randomDataFactory) {
        this.randomDataFactory=randomDataFactory;
    }
  
    private final RandomDataFactory randomDataFactory;
  
    @Override
    public final UUID createUUID() {
        byte[] data=new byte[16];
        randomDataFactory.nextBytes(data);
        return UUIDs.createUUIDVersion4(data);
    }
}
