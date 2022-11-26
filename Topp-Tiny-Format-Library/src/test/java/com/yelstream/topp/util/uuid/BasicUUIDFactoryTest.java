package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.RandomGeneratorFactories;
import com.yelstream.topp.util.random.RandomGeneratorFactory;
import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.RandomDataFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class BasicUUIDFactoryTest {


    abstract static class AbstractUUIDFactorySupplier implements UUIDFactorySupplier {
        public AbstractUUIDFactorySupplier(String name) {
            this.name=name;
        }

        private final String name;

        @Override
        public String toString() {
            return name;
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

        UUIDFactorySupplier composedRandomUUIDFactorySupplier1=new AbstractUUIDFactorySupplier("ComposedRandomUUIDFactory/java.util.Random") {
            @Override
            public UUIDFactory createUUIDFactory() {
                RandomGeneratorFactory randomFactory=RandomGeneratorFactories.createRandomGeneratorFactory();
                RandomDataFactory randomDataFactory=ConcurrentRandomDataFactory.builder().randomFactory(randomFactory).build();
                return new LongsRandomUUIDFactory(randomDataFactory);
            }
        };

        UUIDFactorySupplier composedRandomUUIDFactorySupplier2=new AbstractUUIDFactorySupplier("ComposedRandomUUIDFactory/java.security.SecureRandom") {
            @Override
            public UUIDFactory createUUIDFactory() {
                RandomGeneratorFactory randomFactory=RandomGeneratorFactories.createSecureRandomGeneratorFactory();
                RandomDataFactory randomDataFactory=ConcurrentRandomDataFactory.builder().randomFactory(randomFactory).build();
                return new LongsRandomUUIDFactory(randomDataFactory);
            }
        };

        return Stream.of(
            Arguments.of(UUIDFactorySupplier.of(JDKRandomUUIDFactory::new), listSize),
            Arguments.of(UUIDFactorySupplier.of(()->new ByteArrayRandomUUIDFactory(ConcurrentRandomDataFactory.builder().build())), listSize),
            Arguments.of(UUIDFactorySupplier.of(NanoTimeRandomUUIDFactory::new), listSize),
            Arguments.of(UUIDFactorySupplier.of(CountTimeRandomUUIDFactory::new), listSize),
            Arguments.of(composedRandomUUIDFactorySupplier1, listSize),
            Arguments.of(composedRandomUUIDFactorySupplier2, listSize),
            Arguments.of(UUIDFactorySupplier.of(CountRandomUUIDFactory::new), listSize)
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

    @ParameterizedTest(name="{index}: {00}, size={1}")
    @MethodSource("data")
    void convert(UUIDFactorySupplier g,
                 int listSize) throws Exception {
        UUIDFactory f=g.createUUIDFactory();
        createUUIDList(f,listSize);
    }
}
