package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomGeneratorFactory;
import com.yelstream.topp.util.random.RandomGenerators;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public final class SimpleRandomDataFactory implements RandomDataFactory {
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

    /**
     * Creates a simple factory.
     * @param random Random generator.
     * @return Simple factory of random data.
     */
    public static SimpleRandomDataFactory of(RandomGenerator random) {
        return new SimpleRandomDataFactory(()->random);
    }
}
