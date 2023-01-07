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
public class ByteArrayRandomUUIDFactory implements UUIDFactory {
    private final RandomDataFactory randomDataFactory;

    @Override
    public final UUID createUUID() {
        byte[] data=new byte[16];
        randomDataFactory.nextBytes(data);
        return UUIDs.createUUIDVersion4(data);
    }
}
