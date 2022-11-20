package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * Abstract implementation of {@link RandomUUIDFactory}.
 * 
 * @author Morten Sabroe Mortensen
 */
public abstract class AbstractRandomUUIDFactory extends AbstractUUIDFactory implements RandomUUIDFactory {
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
  
    protected final byte[] convertDataToType4(byte[] data) {
        data[6]&=0x0f;  //Clear version - four bits.
        data[6]|=0x40;  //Set version to '4'.
        data[8]&=0x3f;  //Clear variant - two bits.
        data[8]|=0x80;  //Set variant to 'IETF'.
        return data;
    }
  
    protected final long convertDataToType4_MSB(long msb) {
        msb&=0xff_ff_ff_ff_ff_ff_0f_ffL;  //Clear version - four bits.
        msb|=0x00_00_00_00_00_00_40_00L;  //Set version to '4'.
        return msb;
    }
  
    protected final long convertDataToType4_LSB(long lsb) {
        lsb&=0x3f_ff_ff_ff_ff_ff_ff_ffL;  //Clear variant - two bits.
        lsb|=0x80_00_00_00_00_00_00_00L;  //Set variant to 'IETF'.
        return lsb;
    }
  
    protected final long convertDataToType4_MSB_LeftShift(long msb) {
        long a=(msb<<4)&0xff_ff_ff_ff_ff_ff_00_00L;
        long b=msb&0x00_00_00_00_00_00_0f_ffL;
        msb=a|b;

        return msb;
    }
  
    protected final long convertDataToType4_MSB_LSB_LeftShift(long msb,
                                                              long lsb) {
        msb=(msb << 2)|(lsb >>> 30);
        return convertDataToType4_MSB_LeftShift(msb);
    }
  
    protected final UUID createUUIDVersion4(byte[] data) {
        convertDataToType4(data);
        return UUIDs.createUUID(data);
    }
  
    protected final UUID createUUIDVersion4(long msb, long lsb) {
        msb=convertDataToType4_MSB(msb);
        lsb=convertDataToType4_LSB(lsb);
        return UUIDs.createUUID(msb,lsb);
    }
  
    protected final UUID createUUIDVersion4_MSB_LeftShift(long msb, long lsb) {
        msb=convertDataToType4_MSB_LeftShift(msb);
        lsb=convertDataToType4_LSB(lsb);
        return UUIDs.createUUID(msb,lsb);
    }
  
    protected final UUID createUUIDVersion4_MSB_LSB_LeftShift(long msb, long lsb) {
        msb=convertDataToType4_MSB_LSB_LeftShift(msb,lsb);
        lsb=convertDataToType4_LSB(lsb);
        return UUIDs.createUUID(msb,lsb);
    }
}
