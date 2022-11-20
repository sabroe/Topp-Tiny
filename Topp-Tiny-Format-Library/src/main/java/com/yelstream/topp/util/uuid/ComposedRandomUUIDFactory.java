package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.RandomDataFactory;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class ComposedRandomUUIDFactory extends AbstractRandomUUIDFactory {
    public ComposedRandomUUIDFactory(RandomDataFactory randomDataFactory) {
        this.randomDataFactory=randomDataFactory;
    }
  
    public ComposedRandomUUIDFactory() {
    }
  
    private RandomDataFactory randomDataFactory;
  
    public final RandomDataFactory getRandomDataFactory() {
        return randomDataFactory;
    }

    public final void setRandomDataFactory(RandomDataFactory randomDataFactory) {
        this.randomDataFactory=randomDataFactory;
    }

    @Override
    public final UUID createUUID() {
        byte[] data=new byte[16];
        randomDataFactory.nextBytes(data);
        return createUUIDVersion4(data);
    }
}
