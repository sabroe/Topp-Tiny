/*** FILE "TimeUnitConverterTest.java" ****************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import com.yelstream.util.concurrent.RoundTimeUnitConverter;
import com.yelstream.util.concurrent.TimeUnitConverter;
import com.yelstream.util.concurrent.TruncateTimeUnitConverter;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

/*** TimeUnitConverterTest: ***************************************************/

/**
 * Test of {@link TimeUnitConverter} instances. 
 * 
 * @author Morten Sabroe Mortensen
 */
public class TimeUnitConverterTest
{
  /**
   * Test of {@link TruncateTimeUnitConverter}.
   */
  @Test
  public void testTruncateTimeUnitConverter()
  {
    TimeUnitConverter c=new TruncateTimeUnitConverter(); 
    
    Assert.assertEquals(0,c.convert(   0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assert.assertEquals(0,c.convert( 499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assert.assertEquals(0,c.convert( 500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));  //No rounding!
    Assert.assertEquals(1,c.convert(1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    
    Assert.assertEquals(0,c.convert(60*60*24/2-1,TimeUnit.SECONDS,TimeUnit.DAYS));
    Assert.assertEquals(0,c.convert(60*60*24/2  ,TimeUnit.SECONDS,TimeUnit.DAYS));       //No rounding!

    Assert.assertEquals(0,c.convert(-499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assert.assertEquals(0,c.convert(-500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));  //No rounding!
  }

  /**
   * Test of {@link RoundTimeUnitConverter}.
   */
  @Test
  public void testRoundTimeUnitConverter()
  {
    TimeUnitConverter c=new RoundTimeUnitConverter(); 
    
    Assert.assertEquals(0,c.convert(   0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assert.assertEquals(0,c.convert( 499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assert.assertEquals(1,c.convert( 500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));   //Yes, rounding!
    Assert.assertEquals(1,c.convert(1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    
    Assert.assertEquals(0,c.convert(60*60*24/2-1,TimeUnit.SECONDS,TimeUnit.DAYS));
    Assert.assertEquals(1,c.convert(60*60*24/2  ,TimeUnit.SECONDS,TimeUnit.DAYS));        //Yes, rounding!

    Assert.assertEquals( 0,c.convert(-499,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));
    Assert.assertEquals(-1,c.convert(-500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS));  //Yes, rounding!
  }
}

/******** "TimeUnitConverterTest.java" ****************************************/ 
