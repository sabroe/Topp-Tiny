/*** FILE "TimeUnitConverter.java" ********************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import java.util.concurrent.TimeUnit;

/*** TimeUnitConverter: *******************************************************/

/**
 * Converts {@code long} time duration values.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
public interface TimeUnitConverter
{
  /**
   * Converts a source time duration in a specific source time unit to 
   * a target time duration in a specific target time unit. 
   * @param sourceDuration Source time duration.
   * @param sourceUnit Source time unit.
   * @param targetUnit Target time unit.
   * @return Target time duration.
   */
  long convert(long sourceDuration,
               TimeUnit sourceUnit,
               TimeUnit targetUnit);
}

/******** "TimeUnitConverter.java" ********************************************/ 
