package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.RandomDataFactory;
import lombok.experimental.UtilityClass;

/**
 * Factory of universally unique identifiers.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-23
 */
@UtilityClass
public class UUIDFactories {
    /**
     * Creates a factory of UUIDs.
     * @param randomDataFactory Factory of random data.
     * @return Factory of UUIDs.
     */
    public static UUIDFactory createUUIDFactory(RandomDataFactory randomDataFactory) {
        return new LongsRandomUUIDFactory(randomDataFactory);
    }
}
