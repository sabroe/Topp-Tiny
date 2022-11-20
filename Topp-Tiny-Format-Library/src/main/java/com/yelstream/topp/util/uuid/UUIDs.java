package com.yelstream.topp.util.uuid;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * Utility addressing instances of {@link UUID}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
@UtilityClass
public class UUIDs {

    /**
     * .
     * @param data .
     * @return .
     */
    public static UUID createUUID(byte[] data) {
        long msb=0;
        long lsb=0;

        if (data.length!=16) {
            throw new IllegalArgumentException(String.format("Failure to create UUID; data must be 16 bytes in length, but is %s!", data.length));
        } else {
            for (int i=0; i<8; i++) {
                msb=(msb<<8)|(data[i]&0xff);
            }
            for (int i=8; i<16; i++) {
                lsb=(lsb<<8)|(data[i]&0xff);
            }
        }

        return new UUID(msb,lsb);
    }

    /**
     * .
     * @param msb .
     * @param lsb .
     * @return .
     */
    public static UUID createUUID(long msb, long lsb) {
        return new UUID(msb,lsb);
    }
}
