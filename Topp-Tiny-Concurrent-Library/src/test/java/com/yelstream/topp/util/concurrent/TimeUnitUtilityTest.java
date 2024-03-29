package com.yelstream.topp.util.concurrent;

import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TimeUnitUtility}. 
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-02-28
 */
class TimeUnitUtilityTest {
    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#FLOOR}.
     */
    @Test
    void testConvert_FLOOR() {
        RoundingMode roundingMode=RoundingMode.FLOOR;

        Assertions.assertEquals( 5,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-3,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#CEILING}.
     */
    @Test
    void testConvert_CEILING() {
        RoundingMode roundingMode=RoundingMode.CEILING;

        Assertions.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 3,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-5,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#UP}.
     */
    @Test
    void testConvert_UP() {
        RoundingMode roundingMode=RoundingMode.UP;

        Assertions.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 3,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-3,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#DOWN}.
     */
    @Test
    void testConvert_DOWN() {
        RoundingMode roundingMode=RoundingMode.DOWN;

        Assertions.assertEquals( 5,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-5,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#HALF_UP}.
     */
    @Test
    void testConvert_HALF_UP() {
        RoundingMode roundingMode=RoundingMode.HALF_UP;

        Assertions.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 3,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-3,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#HALF_DOWN}.
     */
    @Test
    void testConvert_HALF_DOWN() {
        RoundingMode roundingMode=RoundingMode.HALF_DOWN;

        Assertions.assertEquals( 5,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-5,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#HALF_EVEN}.
     */
    @Test
    void testConvert_HALF_EVEN() {
        RoundingMode roundingMode=RoundingMode.HALF_EVEN;

        Assertions.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    }

    /**
     * Indicates, if an arithmetic exception is thrown wheen invoking
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}.
     * @param sourceDuration Source time duration.
     * @param sourceUnit Source time unit.
     * @param targetUnit Target time unit.
     * @param roundingMode RoundingMode.
     */
    protected boolean convert_ArithmeticException(long sourceDuration,
                                                  TimeUnit sourceUnit,
                                                  TimeUnit targetUnit,
                                                  RoundingMode roundingMode) {
        boolean res=false;
        try {
            TimeUnitUtility.convert(sourceDuration,sourceUnit,targetUnit,roundingMode);
        } catch (ArithmeticException ex) {
            res=true;
        }
        return res;
    }

    /**
     * Test of
     * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
     * with rounding mode {@link RoundingMode#UNNECESSARY}.
     */
    @Test
    void testConvert_UNNECESSARY() {
        RoundingMode roundingMode=RoundingMode.UNNECESSARY;

        Assertions.assertTrue(convert_ArithmeticException(5500, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(2500, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(1600, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(1100, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertEquals(   1,    TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(   0,    TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertEquals(  -1,    TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(-1100, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(-1600, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(-2500, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
        Assertions.assertTrue(convert_ArithmeticException(-5500, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, roundingMode));
    }
}
