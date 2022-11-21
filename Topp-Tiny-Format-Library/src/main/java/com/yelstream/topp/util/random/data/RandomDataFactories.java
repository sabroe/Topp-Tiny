package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomFactories;
import com.yelstream.topp.util.random.RandomFactory;
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
    public static RandomDataFactory createRandomDataFactory() {
        return createRandomDataFactory(RandomFactories.createRandomFactory());
    }

    public static RandomDataFactory createRandomDataFactory(RandomFactory randomFactory) {
        return new SimpleRandomDataFactory(randomFactory);
    }

    public static RandomDataFactory createConcurrentRandomDataFactory(RandomFactory randomFactory) {
        return new ConcurrentRandomDataFactory(randomFactory);
    }
}
