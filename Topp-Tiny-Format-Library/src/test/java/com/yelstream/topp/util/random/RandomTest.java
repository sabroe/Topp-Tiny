package com.yelstream.topp.util.random;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Test suite for {@code com.yelstream.topp.util.random.RandomFactory}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
class RandomTest {
    private static void generateRandomData(RandomGenerator random,
                                           int repeatCount) {
        byte[] b=new byte[16];  //16 bytes or 128 bits which is about the size of a UUID
        for (int i=0; i<repeatCount; i++) {
            random.nextBytes(b);
        }
    }

    static final int REPEAT_COUNT=1_000_000;
  
    @Test
    void timeRandom() {
        RandomGenerator random=RandomGeneratorFactories.createRandomGeneratorFactory().createRandomGenerator();
        generateRandomData(random, REPEAT_COUNT);
        Assertions.assertTrue(true);
    }
  
    @Test
    void timeSecureRandom() {
        RandomGenerator random=RandomGeneratorFactories.createSecureRandomGeneratorFactory().createRandomGenerator();
        generateRandomData(random, REPEAT_COUNT);
        Assertions.assertTrue(true);
    }
  
    @Test
    void timeSP800SecureRandomWithAES128() {
        AESEngine blockCipher=new AESEngine();

        SP800SecureRandomBuilder builder=new SP800SecureRandomBuilder();
        builder.setSecurityStrength(128);
        byte[] nonce = new byte[blockCipher.getBlockSize()];
        Random random=builder.buildCTR(blockCipher,8*blockCipher.getBlockSize(),nonce,false);

        generateRandomData(random, REPEAT_COUNT);
        Assertions.assertTrue(true);
    }
  
    @Test
    void timeSP800SecureRandomWithSHA512() {
        Digest digest=new SHA512Digest();

        SP800SecureRandomBuilder builder=new SP800SecureRandomBuilder();
        builder.setSecurityStrength(128);
        byte[] nonce = new byte[digest.getDigestSize()];
        Random random=builder.buildHash(digest,nonce,false);

        generateRandomData(random, REPEAT_COUNT);
        Assertions.assertTrue(true);
    }
  
    @Test
    void timeSP800SecureRandomWithSHA1() {
        Digest digest=new SHA1Digest();

        SP800SecureRandomBuilder builder=new SP800SecureRandomBuilder();
        builder.setSecurityStrength(128);
        byte[] nonce = new byte[digest.getDigestSize()];
        Random random=builder.buildHash(digest,nonce,false);

        generateRandomData(random, REPEAT_COUNT);
        Assertions.assertTrue(true);
    }
}
