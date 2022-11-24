package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomGeneratorFactories;
import com.yelstream.topp.util.random.RandomGeneratorFactory;
import com.yelstream.topp.util.random.RandomGenerators;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

/**
 * Factory of random data ready for concurrent usage.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
public final class ConcurrentRandomDataFactory implements RandomDataFactory {
    /**
     * Constructor.
     * @param randomFactory Factory of random generators.
     * @param capacity Number of permits available.
     * @param fair Indicates, if generation will guarantee first-in first-out granting under contention..
     */
    public ConcurrentRandomDataFactory(RandomGeneratorFactory randomFactory,
                                       Integer capacity,
                                       Boolean fair) {
        this.randomFactory=Objects.requireNonNullElseGet(randomFactory, RandomGeneratorFactories::createSecureRandomGeneratorFactory);

        int permits=(capacity==null?DEFAULT_PERMITS:capacity);
        boolean f=(fair==null?DEFAULT_FAIR:fair);
        barrier=new Semaphore(permits,f);

        randomList=new Random[permits];

        randomReferenceList=new int[permits];
        for (int i=0; i<permits; i++) {
            randomReferenceList[i]=i;
        }
    }

    /**
     * Constructor.
     * @param randomFactory Factory of random generators.
     */
    public ConcurrentRandomDataFactory(RandomGeneratorFactory randomFactory) {
        this(randomFactory,
             null,
             null);
    }

    /**
     * Constructor.
     * @param randomFactory Factory of random generators.
     * @param capacity Number of permits available.
     */
    public ConcurrentRandomDataFactory(RandomGeneratorFactory randomFactory,
                                       Integer capacity) {
        this(randomFactory,
             capacity,
             null);
    }

    /**
     * Constructor.
     * @param capacity Number of permits available.
     * @param fair Indicates, if generation will guarantee first-in first-out granting under contention..
     */
    public ConcurrentRandomDataFactory(Integer capacity,
                                       Boolean fair) {
        this(null, capacity, fair);
    }

    /**
     * Constructor.
     * @param capacity Number of permits available.
     */
    public ConcurrentRandomDataFactory(Integer capacity) {
        this(null,capacity,null);
    }

    /**
     * Constructor.
     */
    public ConcurrentRandomDataFactory() {
        this(null,null,null);
    }

    /**
     * Default number of permits available.
     */
    public static final int DEFAULT_PERMITS=1024;

    /**
     * Indicates, if generation will guarantee first-in first-out granting under contention.
     */
    public static final boolean DEFAULT_FAIR=false;
  
    private final RandomGeneratorFactory randomFactory;
  
    private final Semaphore barrier;

    private final RandomGenerator[] randomList;

    private final int[] randomReferenceList;

    private final AtomicInteger randomReferenceIndex=new AtomicInteger();

    private final int allocateRandomIndex() {
        int randomIndex=randomReferenceList[randomReferenceIndex.getAndIncrement()];
        if (randomList[randomIndex]==null) {
            randomList[randomIndex]=randomFactory.createRandomGenerator();
        }
        return randomIndex;
    }

    private final void freeRandomIndex(int randomIndex) {
        randomReferenceList[randomReferenceIndex.decrementAndGet()]=randomIndex;
    }

    private void next(Consumer<RandomGenerator> reader) {
        try {
            barrier.acquireUninterruptibly();
            int randomIndex=allocateRandomIndex();
            try {
                RandomGenerator random=randomList[randomIndex];
                reader.accept(random);
            } finally {
                freeRandomIndex(randomIndex);
            }
        } finally {
            barrier.release();
        }
    }

    @Override
    public void nextBytes(byte[] data) {
        next(random -> random.nextBytes(data));
    }

    @Override
    public void nextLongs(long[] data) {
        next(random -> RandomGenerators.nextLongs(random,data));
    }

    @Override
    public long nextLong() {
        try {
            barrier.acquireUninterruptibly();
            int randomIndex=allocateRandomIndex();
            try {
                RandomGenerator random=randomList[randomIndex];
                return random.nextLong();
            } finally {
                freeRandomIndex(randomIndex);
            }
        } finally {
            barrier.release();
        }
    }
}
