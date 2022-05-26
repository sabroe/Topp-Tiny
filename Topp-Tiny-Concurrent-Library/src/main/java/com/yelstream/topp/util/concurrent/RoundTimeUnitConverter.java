/*** FILE "RoundTimeUnitConverter.java" ***************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import java.util.concurrent.TimeUnit;

/*** RoundTimeUnitConverter: **************************************************/

/**
 * Converts {@code long} time duration values by rounding.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
public class RoundTimeUnitConverter
  implements
    TimeUnitConverter
{
  @Override
  public long convert(long sourceDuration,
                      TimeUnit sourceUnit,
                      TimeUnit targetUnit)
  {
    long res=0;
    
    {
      res=
        TimeUnitUtility.convertWithRounding(sourceDuration,
                                            sourceUnit,
                                            targetUnit);
    }
    
    return res;
  }
}

/******** "RoundTimeUnitConverter.java" ***************************************/ 
