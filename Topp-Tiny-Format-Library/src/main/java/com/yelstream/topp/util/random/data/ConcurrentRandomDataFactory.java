package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomGeneratorFactories;
import com.yelstream.topp.util.random.RandomGeneratorFactory;
import com.yelstream.topp.util.random.RandomGenerators;
import lombok.Builder;

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
@Builder(builderClassName = "Builder")
public final class ConcurrentRandomDataFactory implements RandomDataFactory {
    /**
     * Constructor.
     * @param randomFactory Factory of random generators.
     * @param permits Number of permits available.
     * @param fair Indicates, if generation will guarantee first-in first-out granting under contention..
     */
    private ConcurrentRandomDataFactory(RandomGeneratorFactory randomFactory,
                                        int permits,
                                        boolean fair) {
        this.randomFactory=Objects.requireNonNullElseGet(randomFactory, RandomGeneratorFactories::createSecureRandomGeneratorFactory);
        barrier=new Semaphore(permits,fair);

        randomList=new Random[permits];

        randomReferenceList=new int[permits];
        for (int i=0; i<permits; i++) {
            randomReferenceList[i]=i;
        }
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

    /**
     * Builder of {@link ConcurrentRandomDataFactory} instances.
     */
    public static class Builder {
        private RandomGeneratorFactory randomFactory;
        private int permits=DEFAULT_PERMITS;
        private boolean fair=DEFAULT_FAIR;

        /**
         * Build a new instance.
         * @return Instance.
         */
        public ConcurrentRandomDataFactory build() {
            this.randomFactory=Objects.requireNonNullElseGet(randomFactory, RandomGeneratorFactories::createSecureRandomGeneratorFactory);
            return new ConcurrentRandomDataFactory(randomFactory,permits,fair);
        }
    }

    private int allocateRandomIndex() {
        int randomIndex=randomReferenceList[randomReferenceIndex.getAndIncrement()];
        if (randomList[randomIndex]==null) {
            randomList[randomIndex]=randomFactory.createRandomGenerator();
        }
        return randomIndex;
    }

    private void freeRandomIndex(int randomIndex) {
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
