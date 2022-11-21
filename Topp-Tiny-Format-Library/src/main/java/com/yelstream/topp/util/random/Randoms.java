package com.yelstream.topp.util.random;

import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * Utility addressing instances of {@link Random}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-21
 */
@UtilityClass
public class Randoms {
    /**
     * Gets the next sequence of longs from a random generator.
     * @param random Random generator.
     * @param data Data-buffer to be filled with random long values.
     */
    public static void nextLongs(Random random,
                                 long[] data) {
        for (int i=0; i<data.length; i++) {
            data[i]=random.nextLong();
        }
    }
}
