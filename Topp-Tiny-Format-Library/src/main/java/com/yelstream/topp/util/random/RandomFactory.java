package com.yelstream.topp.util.random;

import java.util.Random;

/**
 * Factory of random generators.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@FunctionalInterface
@SuppressWarnings("unused")
public interface RandomFactory {
    /**
     * Creates a random generator.
     * @return Randomly generator.
     */
    Random createRandom();
}
