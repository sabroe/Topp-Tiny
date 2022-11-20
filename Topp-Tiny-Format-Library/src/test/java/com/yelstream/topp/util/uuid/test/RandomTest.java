package com.yelstream.topp.util.uuid.test;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Random;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
class RandomTest {
    private void generateRandomData(Random r,
                                    int count) {
        byte[] b=new byte[16];

        for (int i=0; i<count; i++) {
            r.nextBytes(b);
        }
    }

    static final int count=1_000_000;
  
    @Test
    void speedSequential_Random() throws Exception {
        Random r=new Random();
        generateRandomData(r,count);
    }
  
    @Test
    void speedSequential_SecureRandom() throws Exception {
        Random r=new SecureRandom();
        generateRandomData(r,count);
    }
  
    @Test
    void speedSequential_SP800SecureRandom_AES() throws Exception {
        SP800SecureRandomBuilder builder=new SP800SecureRandomBuilder();

        AESEngine blockCipher=new AESEngine();

        builder.setSecurityStrength(128);
        Random r=
            builder.buildCTR(blockCipher,
                             8*blockCipher.getBlockSize(),
                             new byte[blockCipher.getBlockSize()],
                             false);

        generateRandomData(r,count);
    }
  
    @Test
    void speedSequential_SP800SecureRandom_SHA512Digest() throws Exception {
        SP800SecureRandomBuilder builder=new SP800SecureRandomBuilder();

        Digest digest=new SHA512Digest();

        builder.setSecurityStrength(128);
        Random r=
            builder.buildHash(digest,
                              new byte[digest.getDigestSize()],
                              false);

        generateRandomData(r,count);
    }
  
    @Test
    void speedSequential_SP800SecureRandom_SHA1Digest() throws Exception {
        SP800SecureRandomBuilder builder=new SP800SecureRandomBuilder();

        Digest digest=new SHA1Digest();

        builder.setSecurityStrength(128);
        Random r=
            builder.buildHash(digest,
                              new byte[digest.getDigestSize()],
                              false);

        generateRandomData(r,count);
    }
}
