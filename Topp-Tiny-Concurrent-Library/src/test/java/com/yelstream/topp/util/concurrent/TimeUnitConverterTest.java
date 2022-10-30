package com.yelstream.topp.util.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Test of {@link TimeUnitConverter} instances. 
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
class TimeUnitConverterTest
{
  /**
   * Test of {@link TruncateTimeUnitConverter}.
   */
  @Test
  void testTruncateTimeUnitConverter()
  {
    TimeUnitConverter c=new TruncateTimeUnitConverter(); 
    
    Assertions.assertEquals(0,c.convert(   0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assertions.assertEquals(0,c.convert( 499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assertions.assertEquals(0,c.convert( 500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));  //No rounding!
    Assertions.assertEquals(1,c.convert(1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    
    Assertions.assertEquals(0,c.convert(60*60*24/2-1,TimeUnit.SECONDS,TimeUnit.DAYS));
    Assertions.assertEquals(0,c.convert(60*60*24/2  ,TimeUnit.SECONDS,TimeUnit.DAYS));       //No rounding!

    Assertions.assertEquals(0,c.convert(-499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assertions.assertEquals(0,c.convert(-500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));  //No rounding!
  }

  /**
   * Test of {@link RoundTimeUnitConverter}.
   */
  @Test
  void testRoundTimeUnitConverter()
  {
    TimeUnitConverter c=new RoundTimeUnitConverter(); 
    
    Assertions.assertEquals(0,c.convert(   0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assertions.assertEquals(0,c.convert( 499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assertions.assertEquals(1,c.convert( 500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));   //Yes, rounding!
    Assertions.assertEquals(1,c.convert(1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    
    Assertions.assertEquals(0,c.convert(60*60*24/2-1,TimeUnit.SECONDS,TimeUnit.DAYS));
    Assertions.assertEquals(1,c.convert(60*60*24/2  ,TimeUnit.SECONDS,TimeUnit.DAYS));        //Yes, rounding!

    Assertions.assertEquals( 0,c.convert(-499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assertions.assertEquals(-1,c.convert(-500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));  //Yes, rounding!
  }
}
