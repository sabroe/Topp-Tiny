package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomFactories;
import com.yelstream.topp.util.random.RandomFactory;
import com.yelstream.topp.util.random.Randoms;
import lombok.Getter;

import java.util.Random;

/**
 * Simple factory of random data.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@SuppressWarnings("java:S1117")
public final class SimpleRandomDataFactory implements RandomDataFactory {
    public SimpleRandomDataFactory() {
        randomFactory=RandomFactories.createSecureRandomFactory();
    }
  
    public SimpleRandomDataFactory(RandomFactory randomFactory) {
        this.randomFactory=randomFactory;
    }
  
    private final RandomFactory randomFactory;

    @Getter(lazy=true)
    private final Random random=randomFactory.createRandom();

    @Override
    public void nextBytes(byte[] data) {
        Random random=getRandom();
        random.nextBytes(data);
    }
  
    @Override
    public void nextLongs(long[] data) {
        Random random=getRandom();
        Randoms.nextLongs(random,data);
    }
}
