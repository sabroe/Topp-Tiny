package com.yelstream.topp.util.random;

import lombok.experimental.UtilityClass;

import java.util.random.RandomGenerator;

/**
 * Utility addressing instances of {@link RandomGenerator}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-21
 */
@UtilityClass
public class RandomGenerators {
    /**
     * Gets the next sequence of longs from a random generator.
     * @param random Random generator.
     * @param data Data-buffer to be filled with random long values.
     */
    public static void nextLongs(RandomGenerator random,
                                 long[] data) {
        for (int i=0; i<data.length; i++) {
            data[i]=random.nextLong();
        }
    }
}
