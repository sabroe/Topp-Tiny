package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.RandomGeneratorFactory;
import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.RandomDataFactory;

import java.util.UUID;

/**
 * .
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
public class ConcurrentRandomUUIDFactory implements UUIDFactory {
    /**
     * Constructor.
     * @param randomFactory Factory of random generators.
     * @param capacity Number of permits available.
     * @param fair Indicates, if generation will guarantee first-in first-out granting under contention..
     */
    public ConcurrentRandomUUIDFactory(RandomGeneratorFactory randomFactory,
                                       Integer capacity,
                                       Boolean fair) {
        randomDataFactory=new ConcurrentRandomDataFactory(randomFactory,capacity,fair);
    }

    /**
     * Constructor.
     * @param capacity Number of permits available.
     * @param fair Indicates, if generation will guarantee first-in first-out granting under contention..
     */
    public ConcurrentRandomUUIDFactory(Integer capacity,
                                       Boolean fair) {
        this(null,capacity,fair);
    }

    /**
     * Constructor.
     * @param capacity Number of permits available.
     */
    public ConcurrentRandomUUIDFactory(Integer capacity) {
        this(null,capacity,null);
    }

    /**
     * Constructor.
     */
    public ConcurrentRandomUUIDFactory() {
        this(null,null,null);
    }
  
    private final RandomDataFactory randomDataFactory;

    @Override
    public final UUID createUUID() {
        byte[] data=new byte[16];
        randomDataFactory.nextBytes(data);
        return UUIDs.createUUIDVersion4(data);
    }
}
