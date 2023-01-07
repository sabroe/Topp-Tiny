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
     * Creates a UUID.
     * @param data Data-buffer with raw values.
     *             This must have length 16.
     * @return UUID.
     */
    public static UUID createUUID(byte[] data) {
        long msb=0;
        long lsb=0;

        if (data.length!=16) {
            throw new IllegalArgumentException(String.format("Failure to create UUID; data-buffer must be 16 bytes in length, but is %s!", data.length));
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
     * Creates a UUID.
     * @param msb Most significant bits of UUID.
     * @param lsb Least significant bits of UUID.
     * @return UUID.
     */
    public static UUID createUUID(long msb, long lsb) {
        return new UUID(msb,lsb);
    }

    /*
     * Note:
     *   Variants and versions:
     *     The variant indicates the layout of the UUID.
     *     The UUID specification covers one particular variant.
     *     Other variants are reserved or exist for backward compatibility reasons
     *     (e.g., for values assigned before the UUID specification was produced).
     *     An example of a UUID that is a different variant is the nil UUID, which
     *     is a UUID that has all 128 bits set to zero.
     *
     *     In the canonical representation, xxxxxxxx-xxxx-Mxxx-Nxxx-xxxxxxxxxxxx,
     *     the most significant bits of N indicates the variant (depending on the
     *     variant; one, two, or three bits are used).
     *     The variant covered by the UUID specification is indicated by the two
     *     most significant bits of N being 1 0 (i.e., the hexadecimal N will
     *     always be 8, 9, A, or B).
     *
     *     The variant covered by the UUID specification has five versions.
     *     For this variant, the four bits of M indicates the UUID version
     *     (i.e., the hexadecimal M will be either 1, 2, 3, 4, or 5).
     */

    private static byte[] convertDataToType4(byte[] data) {
        data[6]&=0x0f;  //Clear version - four bits.
        data[6]|=0x40;  //Set version to '4'.
        data[8]&=0x3f;  //Clear variant - two bits.
        data[8]|=0x80;  //Set variant to 'IETF'.
        return data;
    }

    private static long convertDataToType4MSB(long msb) {
        msb&=0xff_ff_ff_ff_ff_ff_0f_ffL;  //Clear version - four bits.
        msb|=0x00_00_00_00_00_00_40_00L;  //Set version to '4'.
        return msb;
    }

    private static long convertDataToType4LSB(long lsb) {
        lsb&=0x3f_ff_ff_ff_ff_ff_ff_ffL;  //Clear variant - two bits.
        lsb|=0x80_00_00_00_00_00_00_00L;  //Set variant to 'IETF'.
        return lsb;
    }

    private static long convertDataToType4MSBLeftShift(long msb) {
        long a=(msb<<4)&0xff_ff_ff_ff_ff_ff_00_00L;
        long b=msb&0x00_00_00_00_00_00_0f_ffL;
        msb=a|b;
        return msb;
    }

    private static long convertDataToType4MSBLSBLeftShift(long msb, long lsb) {  //???
        msb=(msb<<2)|(lsb>>>30);
        return convertDataToType4MSBLeftShift(msb);
    }

    /**
     * Creates a random UUIDv4.
     * @param data Data-buffer with raw values.
     *             This must have length 16.
     * @return UUID.
     */
    public static UUID createUUIDVersion4(byte[] data) {
        byte[] convertedData=convertDataToType4(data);
        return createUUID(convertedData);
    }

    /**
     * Creates a random UUIDv4.
     * @param msb Most significant bits of UUID.
     * @param lsb Least significant bits of UUID.
     * @return UUID.
     */
    public static UUID createUUIDVersion4(long msb, long lsb) {
        msb=convertDataToType4MSB(msb);
        lsb=convertDataToType4LSB(lsb);
        return createUUID(msb,lsb);
    }

    /**
     * Creates a random UUIDv4.
     * @param msb Most significant bits of UUID.
     * @param lsb Least significant bits of UUID.
     * @return UUID.
     */
    public static UUID createUUIDVersion4MSBLeftShift(long msb, long lsb) {  //???
        msb=convertDataToType4MSBLeftShift(msb);
        lsb=convertDataToType4LSB(lsb);
        return createUUID(msb,lsb);
    }

    /**
     * Creates a random UUIDv4.
     * @param msb Most significant bits of UUID.
     * @param lsb Least significant bits of UUID.
     * @return UUID.
     */
    public static UUID createUUIDVersion4MSBLSBLeftShift(long msb, long lsb) {  //???
        msb=convertDataToType4MSBLSBLeftShift(msb,lsb);
        lsb=convertDataToType4LSB(lsb);
        return createUUID(msb,lsb);
    }
}
