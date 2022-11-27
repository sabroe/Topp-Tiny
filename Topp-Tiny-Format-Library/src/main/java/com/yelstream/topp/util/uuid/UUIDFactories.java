package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.RandomDataFactory;
import com.yelstream.topp.util.random.data.SimpleRandomDataFactory;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

/**
 * Factory of universally unique identifiers.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-23
 */
@UtilityClass
public class UUIDFactories {
    /**
     * Creates a factory of UUIDs.
     * @param randomDataFactory Factory of random data.
     * @return Factory of UUIDs.
     */
    public static UUIDFactory createUUIDFactory(RandomDataFactory randomDataFactory) {
        return new JDKRandomUUIDFactory();
    }

    public static UUIDFactory createSimpleUUIDFactory(RandomDataFactory randomDataFactory) {
        return new ByteArrayRandomUUIDFactory(new SimpleRandomDataFactory(SecureRandom::new));
    }

    public static UUIDFactory createConcurrentUUIDFactory(RandomDataFactory randomDataFactory) {
        return new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().randomFactory(SecureRandom::new).build());
    }
}
