package com.yelstream.topp.util.uuid.test;

import com.yelstream.topp.util.uuid.UUIDFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
class SequentialCorrectnessUUIDFactoryTest extends AbstractUUIDFactoryTest {
    @ParameterizedTest(name="{index}: {0}, size={1}")
    @MethodSource("data")
    void correctnessSequential(UUIDFactorySupplier g,
                               int listSize,
                               int threadCount) throws Exception {
        UUIDFactory f=g.createUUIDFactory();
        UUID[] l=createUUIDList(f,listSize);
        verifyUUIDList(l);
    }
}
