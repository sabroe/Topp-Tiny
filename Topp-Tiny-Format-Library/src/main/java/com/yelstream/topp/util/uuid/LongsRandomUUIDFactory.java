package com.yelstream.topp.util.uuid;

import com.yelstream.topp.util.random.data.RandomDataFactory;
import lombok.AllArgsConstructor;

import java.util.UUID;

/**
 * .
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-23
 */
@AllArgsConstructor
public class LongsRandomUUIDFactory implements UUIDFactory {
    private final RandomDataFactory randomDataFactory;

    @Override
    public final UUID createUUID() {
        long msb=randomDataFactory.nextLong();
        long lsb=randomDataFactory.nextLong();
        return UUIDs.createUUIDVersion4(msb,lsb);
    }
}
