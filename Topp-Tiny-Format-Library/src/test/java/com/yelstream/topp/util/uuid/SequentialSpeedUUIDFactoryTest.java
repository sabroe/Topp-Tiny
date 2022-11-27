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
class SequentialSpeedUUIDFactoryTest {
    /**
     * .
     */
    public static Stream<Arguments> data() {
        int listSize=1_000_000;

        return Stream.of(
            Arguments.of("CountTimeRandomUUIDFactory", UUIDFactorySupplier.of(CountTimeRandomUUIDFactory::new), listSize),
            Arguments.of("NanoTimeRandomUUIDFactory", UUIDFactorySupplier.of(NanoTimeRandomUUIDFactory::new), listSize),
            Arguments.of("CountRandomUUIDFactory", UUIDFactorySupplier.of(CountRandomUUIDFactory::new), listSize),

            Arguments.of("LongsRandomUUIDFactory", UUIDFactorySupplier.of(()->new LongsRandomUUIDFactory(RandomDataFactories.createSimpleRandomDataFactory())), listSize),
            Arguments.of("LongArrayRandomUUIDFactory", UUIDFactorySupplier.of(()->new LongArrayRandomUUIDFactory(RandomDataFactories.createSimpleRandomDataFactory())), listSize),
            Arguments.of("ByteArrayRandomUUIDFactory", UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(RandomDataFactories.createSimpleRandomDataFactory())), listSize),

            Arguments.of("LongsRandomUUIDFactory/Secure", UUIDFactorySupplier.of(()->new LongsRandomUUIDFactory(SimpleRandomDataFactory.of(new SecureRandom()))), listSize),
            Arguments.of("LongArrayRandomUUIDFactory/Secure", UUIDFactorySupplier.of(()->new LongArrayRandomUUIDFactory(SimpleRandomDataFactory.of(new SecureRandom()))), listSize),
            Arguments.of("ByteArrayRandomUUIDFactory/Secure", UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(SimpleRandomDataFactory.of(new SecureRandom()))), listSize),

            Arguments.of("JDKRandomUUIDFactory", UUIDFactorySupplier.of(JDKRandomUUIDFactory::new), listSize),
            Arguments.of("ConcurrentRandomUUIDFactory", UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().randomFactory(Random::new).build())), listSize),

            Arguments.of("ConcurrentRandomUUIDFactory/Secure", UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().randomFactory(SecureRandom::new).build())), listSize)
        );
    }

    @ParameterizedTest(name="{index}: {0}, size={2}")
    @MethodSource("data")
    void speedSequential(String name,
                         UUIDFactorySupplier uuidFactorySupplier,
                         int listSize) {
        UUIDFactory uuidFactory=uuidFactorySupplier.createUUIDFactory();
        UUIDFactoryTestUtility.createUUIDList(uuidFactory,listSize);
        Assertions.assertTrue(true);
    }
}
