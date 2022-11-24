package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomGeneratorFactories;
import com.yelstream.topp.util.random.RandomGeneratorFactory;
import com.yelstream.topp.util.random.RandomGenerators;
import lombok.Getter;

import java.util.random.RandomGenerator;

/**
 * Simple factory of random data.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@SuppressWarnings("java:S1117")
public final class SimpleRandomDataFactory implements RandomDataFactory {
    /**
     * Constructor.
     */
    public SimpleRandomDataFactory() {
        randomFactory= RandomGeneratorFactories.createSecureRandomGeneratorFactory();
    }

    /**
     * Constructor.
     * @param randomFactory Factory of random generators
     */
    public SimpleRandomDataFactory(RandomGeneratorFactory randomFactory) {
        this.randomFactory=randomFactory;
    }

    /**
     * Constructor.
     * @param random Random generator.
     */
    public SimpleRandomDataFactory(RandomGenerator random) {
        this.randomFactory=()->random;
    }

    private final RandomGeneratorFactory randomFactory;

    @Getter(lazy=true)
    private final RandomGenerator random=randomFactory.createRandomGenerator();

    @Override
    public void nextBytes(byte[] data) {
        RandomGenerator random=getRandom();
        random.nextBytes(data);
    }

    @Override
    public void nextLongs(long[] data) {
        RandomGenerator random=getRandom();
        RandomGenerators.nextLongs(random,data);
    }

    @Override
    public long nextLong() {
        RandomGenerator random=getRandom();
        return random.nextLong();
    }
}
