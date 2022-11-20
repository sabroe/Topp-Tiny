package com.yelstream.topp.util.uuid.test;

import com.yelstream.topp.util.random.RandomFactories;
import com.yelstream.topp.util.random.RandomFactory;
import com.yelstream.topp.util.random.data.ConcurrentRandomDataFactory;
import com.yelstream.topp.util.random.data.RandomDataFactory;
import com.yelstream.topp.util.uuid.ComposedRandomUUIDFactory;
import com.yelstream.topp.util.uuid.ConcurrentRandomUUIDFactory;
import com.yelstream.topp.util.uuid.CountRandomUUIDFactory;
import com.yelstream.topp.util.uuid.CountTimeRandomUUIDFactory;
import com.yelstream.topp.util.uuid.JDKRandomUUIDFactory;
import com.yelstream.topp.util.uuid.NanoTimeRandomUUIDFactory;
import com.yelstream.topp.util.uuid.UUIDFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class BasicUUIDFactoryTest {
    interface UUIDFactorySupplier {
        public UUIDFactory createUUIDFactory() throws Exception;
    }

    static class ClassUUIDFactorySupplier implements UUIDFactorySupplier {
        public ClassUUIDFactorySupplier(Class<? extends UUIDFactory> clazz) {
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

    abstract static class AbstractUUIDFactorySupplier implements UUIDFactorySupplier {
        public AbstractUUIDFactorySupplier(String name) {
            this.name=name;
        }

        private String name;

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
            public UUIDFactory createUUIDFactory() throws Exception {
                RandomFactory randomFactory=RandomFactories.createRandomFactory();
                RandomDataFactory randomDataFactory=new ConcurrentRandomDataFactory(randomFactory);
                return new ComposedRandomUUIDFactory(randomDataFactory);
            }
        };

        UUIDFactorySupplier composedRandomUUIDFactorySupplier2=new AbstractUUIDFactorySupplier("ComposedRandomUUIDFactory/java.security.SecureRandom") {
            @Override
            public UUIDFactory createUUIDFactory() throws Exception {
                RandomFactory randomFactory=RandomFactories.createSecureRandomFactory();
                RandomDataFactory randomDataFactory=new ConcurrentRandomDataFactory(randomFactory);
                return new ComposedRandomUUIDFactory(randomDataFactory);
            }
        };

        return Stream.of(
            Arguments.of(new ClassUUIDFactorySupplier(JDKRandomUUIDFactory.class)       , listSize),
            Arguments.of(new ClassUUIDFactorySupplier(ConcurrentRandomUUIDFactory.class), listSize),
            Arguments.of(new ClassUUIDFactorySupplier(NanoTimeRandomUUIDFactory.class)  , listSize),
            Arguments.of(new ClassUUIDFactorySupplier(CountTimeRandomUUIDFactory.class) , listSize),
            Arguments.of(composedRandomUUIDFactorySupplier1                             , listSize),
            Arguments.of(composedRandomUUIDFactorySupplier2                             , listSize),
            Arguments.of(new ClassUUIDFactorySupplier(CountRandomUUIDFactory.class)     , listSize)
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

    @ParameterizedTest(name="{index}: {0}, size={1}")
    @MethodSource("data")
    void convert(UUIDFactorySupplier g,
                 int listSize) throws Exception {
        UUIDFactory f=g.createUUIDFactory();
        createUUIDList(f,listSize);
    }
}
