package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomGeneratorFactories;
import com.yelstream.topp.util.random.RandomGeneratorFactory;
import lombok.experimental.UtilityClass;

/**
 * Utility addressing instances of {@link RandomDataFactory}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@UtilityClass
public class RandomDataFactories {
    /**
     * Creates a factory of random data.
     * @return Factory of random data.
     */
    public static RandomDataFactory createSimpleRandomDataFactory() {
        return createSimpleRandomDataFactory(RandomGeneratorFactories.createRandomGeneratorFactory());
    }

    /**
     * Creates a factory of random data.
     * @param randomFactory Factory of random generators.
     * @return Factory of random data.
     */
    public static RandomDataFactory createSimpleRandomDataFactory(RandomGeneratorFactory randomFactory) {
        return new SimpleRandomDataFactory(randomFactory);
    }

    /**
     * Creates a factory of random data for concurrent access.
     * @param randomFactory Factory of random generators.
     * @return Factory of random data.
     */
    public static RandomDataFactory createConcurrentRandomDataFactory(RandomGeneratorFactory randomFactory) {
        return ConcurrentRandomDataFactory.builder().randomFactory(randomFactory).build();
    }
}
