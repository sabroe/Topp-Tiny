package com.yelstream.topp.util.uuid.test;

import com.yelstream.topp.util.uuid.ConcurrentRandomUUIDFactory;
import com.yelstream.topp.util.uuid.JDKRandomUUIDFactory;
import com.yelstream.topp.util.uuid.UUIDFactory;
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
    static class UUIDFactorySupplier {
        public UUIDFactorySupplier(Class<? extends UUIDFactory> clazz) {
            this.clazz=clazz;
        }

        private Class<? extends UUIDFactory> clazz;

        public UUIDFactory createUUIDFactory() throws Exception {
            return clazz.newInstance();
        }

        @Override
        public String toString() {
            return clazz.getSimpleName();
        }
    }

    /**
     * .
     * @return .
     */
    public static Stream<Arguments> data() {
        //int listSize=10_000_000;
        int listSize=1_000_000;
        //int listSize=5_000_000;

        return Stream.of(
            Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,    1),
            Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,    2),
            Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,    4),
            Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,    8),
            Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,   16),
            Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,   32),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,   64),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,  128),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,  256),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize,  512),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize, 1024),
            //Arguments.of(new UUIDFactorySupplier(JDKRandomUUIDFactory.class)    , listSize, 2048),


            Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,    1),
            Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,    2),
            Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,    4),
            Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,    8),
            Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,   16),
            Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,   32)//,
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,   64),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,  128),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,  256),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize,  512),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize, 1024),
        //Arguments.of(new UUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize, 2048)
        );
    }
  
    UUID[] createUUIDList(UUIDFactory g,
                          int listSize) throws Exception {
        UUID[] l=new UUID[listSize];

        for (int i=0; i<listSize; i++) {
            l[i]=g.createUUID();
        }

        return l;
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

    String[] convert(UUID[] l) {
        String[] res=new String[l.length];
        for (int i=0; i<l.length; i++) {
            res[i]=l[i].toString();
        }
        return res;
    }
  
    void verifyUUIDList(UUID[] l) throws Exception {
        String[] l2=convert(l);

        Arrays.sort(l2);

        for (int i=0; i<l2.length-1; i++) {
            String x=l2[i];
            String y=l2[i+1];

            assert x.compareTo(y)<0;
        }
    }
}
