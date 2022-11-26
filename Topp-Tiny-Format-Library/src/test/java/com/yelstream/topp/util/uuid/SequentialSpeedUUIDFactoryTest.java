package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.RandomDataFactories;
import com.yelstream.topp.util.random.data.SimpleRandomDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Stream;

/**
 * .
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-23
 */
class SequentialSpeedUUIDFactoryTest extends AbstractUUIDFactoryTest {
    /**
     * .
     */
    public static Stream<Arguments> data() {
        int listSize=1_000_000;
        //int listSize=3_000_000;
        //int listSize=5_000_000;

        return Stream.of(
            Arguments.of("CountTimeRandomUUIDFactory", new UUIDFactorySupplier2(CountTimeRandomUUIDFactory::new), listSize),
            Arguments.of("NanoTimeRandomUUIDFactory", new UUIDFactorySupplier2(NanoTimeRandomUUIDFactory::new), listSize),
            Arguments.of("CountRandomUUIDFactory", new UUIDFactorySupplier2(CountRandomUUIDFactory::new), listSize),

            Arguments.of("LongsRandomUUIDFactory", new UUIDFactorySupplier2(()->new LongsRandomUUIDFactory(RandomDataFactories.createSimpleRandomDataFactory())), listSize),
            Arguments.of("LongArrayRandomUUIDFactory", new UUIDFactorySupplier2(()->new LongArrayRandomUUIDFactory(RandomDataFactories.createSimpleRandomDataFactory())), listSize),
            Arguments.of("ByteArrayRandomUUIDFactory", new UUIDFactorySupplier2(()->new ByteArrayRandomUUIDFactory(RandomDataFactories.createSimpleRandomDataFactory())), listSize),

            Arguments.of("LongsRandomUUIDFactory/Secure", new UUIDFactorySupplier2(()->new LongsRandomUUIDFactory(SimpleRandomDataFactory.of(new SecureRandom()))), listSize),
            Arguments.of("LongArrayRandomUUIDFactory/Secure", new UUIDFactorySupplier2(()->new LongArrayRandomUUIDFactory(SimpleRandomDataFactory.of(new SecureRandom()))), listSize),
            Arguments.of("ByteArrayRandomUUIDFactory/Secure", new UUIDFactorySupplier2(()->new ByteArrayRandomUUIDFactory(SimpleRandomDataFactory.of(new SecureRandom()))), listSize),

            Arguments.of("JDKRandomUUIDFactory", new UUIDFactorySupplier2(JDKRandomUUIDFactory::new), listSize),
            Arguments.of("ConcurrentRandomUUIDFactory", new UUIDFactorySupplier2(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().randomFactory(Random::new).build())), listSize),

            Arguments.of("ConcurrentRandomUUIDFactory/Secure", new UUIDFactorySupplier2(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().randomFactory(SecureRandom::new).build())), listSize)
        );
    }

    @ParameterizedTest(name="{index}: {0}, size={2}")
    @MethodSource("data")
    void speedSequential(String name,
                         UUIDFactorySupplier2 uuidFactorySupplier,
                         int listSize) {
        UUIDFactory uuidFactory=uuidFactorySupplier.createUUIDFactory();
        createUUIDList(uuidFactory,listSize);
        Assertions.assertTrue(true);
    }
}
