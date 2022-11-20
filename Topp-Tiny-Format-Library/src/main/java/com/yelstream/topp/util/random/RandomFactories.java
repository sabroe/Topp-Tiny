package com.yelstream.topp.util.random;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Utility addressing instances of {@link RandomFactory}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@UtilityClass
public class RandomFactories {
    public static RandomFactory createRandomFactory() {
        return Random::new;
    }

    public static RandomFactory createSecureRandomFactory() {
        return SecureRandom::new;
    }
}
