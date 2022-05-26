/*** FILE "TimeUnitUtilityTest.java" ******************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import com.yelstream.util.concurrent.TimeUnitUtility;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

/*** TimeUnitUtilityTest: *****************************************************/

/**
 * Test of {@link TimeUnitUtility}. 
 * 
 * @author Morten Sabroe Mortensen
 */
public class TimeUnitUtilityTest
{
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#FLOOR}.
   */
  @Test
  public void testConvert_FLOOR()
  {
    RoundingMode roundingMode=RoundingMode.FLOOR; 
    
    Assert.assertEquals( 5,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-3,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#CEILING}.
   */
  @Test
  public void testConvert_CEILING()
  {
    RoundingMode roundingMode=RoundingMode.CEILING; 
    
    Assert.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 3,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-5,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#UP}.
   */
  @Test
  public void testConvert_UP()
  {
    RoundingMode roundingMode=RoundingMode.UP; 
    
    Assert.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 3,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-3,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#DOWN}.
   */
  @Test
  public void testConvert_DOWN()
  {
    RoundingMode roundingMode=RoundingMode.DOWN; 
    
    Assert.assertEquals( 5,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-5,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#HALF_UP}.
   */
  @Test
  public void testConvert_HALF_UP()
  {
    RoundingMode roundingMode=RoundingMode.HALF_UP; 
    
    Assert.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 3,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-3,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#HALF_DOWN}.
   */
  @Test
  public void testConvert_HALF_DOWN()
  {
    RoundingMode roundingMode=RoundingMode.HALF_DOWN; 
    
    Assert.assertEquals( 5,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-5,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#HALF_EVEN}.
   */
  @Test
  public void testConvert_HALF_EVEN()
  {
    RoundingMode roundingMode=RoundingMode.HALF_EVEN; 
    
    Assert.assertEquals( 6,TimeUnitUtility.convert( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 2,TimeUnitUtility.convert( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 1,TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals( 0,TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-1,TimeUnitUtility.convert(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-2,TimeUnitUtility.convert(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(-6,TimeUnitUtility.convert(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
  
  /**
   *  
   */
  protected boolean convert_ArithmeticException(long sourceDuration,
                                                TimeUnit sourceUnit,
                                                TimeUnit targetUnit,
                                                RoundingMode roundingMode)
  {
    boolean res=false;
    
    {
      try
      {
        TimeUnitUtility.convert(sourceDuration,sourceUnit,targetUnit,roundingMode);
      }
      catch (ArithmeticException ex)
      {
        res=true;
      }
    }
    
    return res;
  }
  
  /**
   * Test of 
   * {@link TimeUnitUtility#convert(long, TimeUnit, TimeUnit, RoundingMode)}
   * with rounding mode {@link RoundingMode#UNNECESSARY}.
   */
  @Test
  public void testConvert_UNNECESSARY()
  {
    RoundingMode roundingMode=RoundingMode.UNNECESSARY; 
    
    Assert.assertEquals(true,convert_ArithmeticException( 5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException( 2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException( 1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException( 1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(   1,    TimeUnitUtility.convert( 1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(   0,    TimeUnitUtility.convert(    0,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(  -1,    TimeUnitUtility.convert(-1000,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException(-1100,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException(-1600,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException(-2500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
    Assert.assertEquals(true,convert_ArithmeticException(-5500,TimeUnit.MICROSECONDS,TimeUnit.MILLISECONDS,roundingMode));
  }
}

/******** "TimeUnitUtilityTest.java" ******************************************/ 
