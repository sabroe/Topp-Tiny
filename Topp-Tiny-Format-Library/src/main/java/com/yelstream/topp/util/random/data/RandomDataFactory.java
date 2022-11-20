package com.yelstream.topp.util.random.data;

/**
 * Factory of random data.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
public interface RandomDataFactory {
    /**
     * Generates random data.
     * @param data Data buffer for random data.
     */
    void nextBytes(byte[] data);

    /**
     * Generates random data.
     * @param data Data buffer for random data.
     */
    void nextLongs(long[] data);
}
