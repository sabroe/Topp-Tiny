package com.yelstream.topp.util.random;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

/**
 * Utility addressing instances of {@link RandomGeneratorFactory}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2018-11-23
 */
@UtilityClass
public class RandomGeneratorFactories {
    /**
     * Creates a factory of random generators in a default, basic version.
     * @return Factory of random generators.
     */
    public static RandomGeneratorFactory createRandomGeneratorFactory() {
        return Random::new;
    }

    /**
     * Creates a factory of random generators in a cryptographically secure version.
     * @return Factory of random generators.
     */
    public static RandomGeneratorFactory createSecureRandomGeneratorFactory() {
        return SecureRandom::new;
    }

    /**
     * Creates a factory of random generators.
     * @param randomGeneratorSupplier Supplier of random generators.
     * @return Factory of random generators.
     */
    public static RandomGeneratorFactory createRandomGeneratorFactory(Supplier<RandomGenerator> randomGeneratorSupplier) {
        return randomGeneratorSupplier::get;
    }
}
