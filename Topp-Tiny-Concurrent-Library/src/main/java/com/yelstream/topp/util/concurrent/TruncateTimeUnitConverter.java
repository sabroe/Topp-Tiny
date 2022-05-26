/*** FILE "TruncateTimeUnitConverter.java" ************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import java.util.concurrent.TimeUnit;

/*** TruncateTimeUnitConverter: ***********************************************/

/**
 * Converts {@code long} time duration values by truncation.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
public class TruncateTimeUnitConverter
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
      res=targetUnit.convert(sourceDuration,sourceUnit);
    }
    
    return res;
  }
}

/******** "TruncateTimeUnitConverter.java" ************************************/ 
