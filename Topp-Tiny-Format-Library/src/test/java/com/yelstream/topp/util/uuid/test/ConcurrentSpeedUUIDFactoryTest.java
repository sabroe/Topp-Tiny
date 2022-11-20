package com.yelstream.topp.util.uuid.test;

import com.yelstream.topp.util.uuid.UUIDFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
class ConcurrentSpeedUUIDFactoryTest extends AbstractUUIDFactoryTest {
    @ParameterizedTest(name="{index}: {0}, size={1}")
    @MethodSource("data")
    void speedConcurrent(UUIDFactorySupplier g,
                         int listSize,
                         int threadCount) throws Exception {
        UUIDFactory f=g.createUUIDFactory();
        createUUIDListInParallel(f,listSize,threadCount);
    }
}
