package com.yelstream.topp.util.random;

import java.util.random.RandomGenerator;

/**
 * Factory of random generators.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2018-11-23
 */
@FunctionalInterface
@SuppressWarnings("unused")
public interface RandomGeneratorFactory {
    /**
     * Creates a random generator.
     * @return Randomly generator.
     */
    RandomGenerator createRandomGenerator();
}
