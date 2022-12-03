package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.SimpleRandomDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Comparison of the sequential/non-parallel speed of UUID factory instances.
 * These should take about the same time.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-26
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlgorithmComparisonTest {
    /**
     * .
     */
    public static Stream<Arguments> data() {
        int listSize=500_000;

        return Stream.of(
            Arguments.of("JDKRandomUUIDFactory",
                         UUIDFactorySupplier.of(JDKRandomUUIDFactory::new), listSize),
            Arguments.of("ByteArrayRandomUUIDFactory/SimpleRandomDataFactory/SecureRandom",
                         UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(new SimpleRandomDataFactory(SecureRandom::new))), listSize),
            Arguments.of("ByteArrayRandomUUIDFactory/ConcurrentRandomDataFactory/SecureRandom",
                         UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().randomFactory(SecureRandom::new).build())), listSize)
        );
    }

    private static Map<String,Long> time=new HashMap<>();

    @Order(1)
    @ParameterizedTest(name="{index}: {0}, size={2}")
    @MethodSource("data")
    void measureDuration(String name,
                         UUIDFactorySupplier uuidFactorySupplier,
                         int listSize) {
        UUIDFactory uuidFactory=uuidFactorySupplier.createUUIDFactory();
        long t0=System.currentTimeMillis();
        UUIDFactoryTestUtility.createUUIDList(uuidFactory,listSize);
        long t1=System.currentTimeMillis();
        time.put(name,t1-t0);
        Assertions.assertTrue(true);
    }

    double getMaxMinRatio() {
        Map.Entry<String,Long> minTime=time.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).get();
        Map.Entry<String,Long> maxTime=time.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get();
        return ((double)maxTime.getValue())/minTime.getValue();
    }

    @Order(2)
    @Test
    void compareDurations() {
        double ratio=getMaxMinRatio();
        Assertions.assertTrue(ratio<1.25);
    }
}
