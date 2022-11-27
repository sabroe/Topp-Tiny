package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class AbstractUUIDFactoryTest {
    /**
     * .
     * @return .
     */
    public static Stream<Arguments> data() {
        //int listSize=10_000_000;
        int listSize=1_000_000;
        //int listSize=5_000_000;

        return Stream.of(
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new)    , listSize,    1),
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new)    , listSize,    2),
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new)    , listSize,    4),
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new)    , listSize,    8),
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new)    , listSize,   16),
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new)    , listSize,   32),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,   64),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,  128),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,  256),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,  512),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize, 1024),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize, 2048),


            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize,    1),
            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize,    2),
            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize,    4),
            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize,    8),
            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize,   16),
            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize,   32)
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,   64),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,  128),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,  256),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,  512),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize, 1024),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize, 2048)
        );
    }
  
    static class Test1Runnable implements Runnable {
        public Test1Runnable(UUIDFactory uuidFactory,
                             int threadIndex,
                             int genCount,
                             UUID[] idList,
                             CountDownLatch startSignal,
                             CountDownLatch doneSignal) {
            this.uuidFactory=uuidFactory;
            this.threadIndex=threadIndex;
            this.genCount=genCount;
            this.idList=idList;
            this.startSignal=startSignal;
            this.doneSignal=doneSignal;
        }

        private UUIDFactory uuidFactory;
        private int threadIndex;
        private int genCount;
        private UUID[] idList;
        private CountDownLatch startSignal;
        private CountDownLatch doneSignal;

        @Override
        public void run() {
            try {
                startSignal.await();
                //System.out.println("  Begin run #"+threadIndex);

                for (int a=0; a<genCount; a++) {
                    idList[threadIndex*genCount+a]= uuidFactory.createUUID();
                }

                //System.out.println("  End run #"+threadIndex+" "+(threadIndex*genCount)+"-"+(threadIndex*genCount+(genCount-1)));
                doneSignal.countDown();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    UUID[] createUUIDListInParallel(UUIDFactory g,
                                    int listSize,
                                    int threadCount) throws Exception {
        int genCount=listSize/threadCount;
        assert listSize%threadCount==0;

        UUID[] idList=new UUID[threadCount*genCount];

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal=new CountDownLatch(threadCount);

        for (int i=0; i<threadCount; i++) {
            Runnable r=new Test1Runnable(g,i,genCount,idList,startSignal,doneSignal);

            Thread t=new Thread(r);
            t.start();
        }

        //System.out.println("Begin!");
        startSignal.countDown();
        doneSignal.await();
        //System.out.println("End!");

        return idList;
    }
}
