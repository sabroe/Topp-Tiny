/*** FILE "RoundingModeTimeUnitConverter.java" ********************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/*** RoundingModeTimeUnitConverter: *******************************************/

/**
 * Converts {@code long} time duration values by rounding.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
public class RoundingModeTimeUnitConverter
  implements
    TimeUnitConverter
{
  public RoundingModeTimeUnitConverter(RoundingMode roundingMode)
  {
    super();
    
    this.roundingMode=roundingMode;
  }
  
  protected RoundingMode roundingMode;
  
  @Override
  public long convert(long sourceDuration,
                      TimeUnit sourceUnit,
                      TimeUnit targetUnit)
  {
    long res=0;
    
    {
      res=
        TimeUnitUtility.convert(sourceDuration,
                                sourceUnit,
                                targetUnit,
                                roundingMode);
    }
    
    return res;
  }
}

/******** "RoundingModeTimeUnitConverter.java" ********************************/ 
