package com.yelstream.topp.util.random.data;

import com.yelstream.topp.util.random.RandomFactories;
import com.yelstream.topp.util.random.RandomFactory;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Factory of random data ready for concurrent usage.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
public final class ConcurrentRandomDataFactory implements RandomDataFactory {
    /**
     * .
     * @param randomFactory .
     * @param capacity .
     * @param fair .
     */
    public ConcurrentRandomDataFactory(RandomFactory randomFactory,
                                       Integer capacity,
                                       Boolean fair) {
        if (randomFactory==null) {
            this.randomFactory=RandomFactories.createSecureRandomFactory();
        } else {
            this.randomFactory=randomFactory;
        }

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
     * .
     * @param randomFactory .
     */
    public ConcurrentRandomDataFactory(RandomFactory randomFactory) {
        this(randomFactory,
             null,
             null);
    }

    /**
     * .
     * @param randomFactory .
     * @param capacity .
     */
    public ConcurrentRandomDataFactory(RandomFactory randomFactory,
                                       Integer capacity) {
        this(randomFactory,
             capacity,
             null);
    }

    /**
     * .
     * @param capacity .
     * @param fair .
     */
    public ConcurrentRandomDataFactory(Integer capacity,
                                       Boolean fair) {
        this(null, capacity, fair);
    }

    /**
     * .
     * @param capacity .
     */
    public ConcurrentRandomDataFactory(Integer capacity) {
        this(null,capacity,null);
    }

    /**
     * .
     */
    public ConcurrentRandomDataFactory() {
        this(null,null,null);
    }

    public static final int DEFAULT_PERMITS=1024;  //Yes, default permits is 1024!

    public static final boolean DEFAULT_FAIR=false;  //Yes, per default this uses non-fair fairness!
  
    private final RandomFactory randomFactory;
  
    private final Semaphore barrier;

    private final Random[] randomList;

    private final int[] randomReferenceList;

    private final AtomicInteger randomReferenceIndex=new AtomicInteger();

    private final int allocateRandomIndex() {
        int randomIndex=randomReferenceList[randomReferenceIndex.getAndIncrement()];
        if (randomList[randomIndex]==null) {
            randomList[randomIndex]=randomFactory.createRandom();
        }
        return randomIndex;
    }

    private final void freeRandomIndex(int randomIndex) {
        randomReferenceList[randomReferenceIndex.decrementAndGet()]=randomIndex;
    }

    private void next(Consumer<Random> consumer) {
        try {
            barrier.acquireUninterruptibly();

            int randomIndex=allocateRandomIndex();
            try {
                Random random=randomList[randomIndex];
                consumer.accept(random);
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
        next(random -> {
            for (int i=0; i<data.length; i++) {
                data[i]=random.nextLong();
            }
        });
    }
}
