/*** FILE "TimeUnitUtility.java" **********************************************/ 

/******************************************************************************/
/**                                                                          **/
/**   Yelstream Software, Morten Sabroe Mortensen.                           **/
/**                                                                          **/
/******************************************************************************/

package com.yelstream.topp.util.concurrent;

import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/*** TimeUnitUtility: *********************************************************/

/**
 * Utility for manipulating instances of {@link TimeUnit}.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2013-02-28
 */
public class TimeUnitUtility
{
  private TimeUnitUtility() {}

  /**
   * Converts a source time duration in a specific source time unit to 
   * a target time duration in a specific target time unit and 
   * with the use of rounding. 
   * @param sourceDuration Source time duration.
   * @param sourceUnit Source time unit.
   * @param targetUnit Target time unit.
   * @return Target time duration.
   */
  public static long convertWithRounding(long sourceDuration,
                                         TimeUnit sourceUnit,
                                         TimeUnit targetUnit)
  {
    long res=0;
    
    {
      res=
        convertWithRounding1(sourceDuration,
                             sourceUnit,
                             targetUnit);
    }
    
    return res;
  }

  /**
   * Converts a source time duration in a specific source time unit to 
   * a target time duration in a specific target time unit and 
   * with the use of rounding. 
   * @param sourceDuration Source time duration.
   * @param sourceUnit Source time unit.
   * @param targetUnit Target time unit.
   * @return Target time duration.
   */
  public static long convertWithRounding1(long sourceDuration,
                                          TimeUnit sourceUnit,
                                          TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convertWithRounding(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);  //Comparison is sound; units are declared in order of granularity with 'NANOSECONDS' first and 'DAYS' last!
      
          if (order<=0)  //if the source has a coarser granularity than the target ...
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);
          }
          else
          {
            long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
            res=targetUnit.convert(sourceDuration+targetToSourceFactor/2,sourceUnit);
          }
        }
      }
    }
    
    return res;
  }
  
  /**
   * Converts a source time duration in a specific source time unit to 
   * a target time duration in a specific target time unit and 
   * with the use of rounding. 
   * @param sourceDuration Source time duration.
   * @param sourceUnit Source time unit.
   * @param targetUnit Target time unit.
   * @return Target time duration.
   */
  public static long convertWithRounding2(long sourceDuration,
                                          TimeUnit sourceUnit,
                                          TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convertWithRounding2(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          TimeUnit finestUnit=TimeUnit.NANOSECONDS;
          long finestDuration=finestUnit.convert(sourceDuration,sourceUnit);

          long targetToFinestFactor=finestUnit.convert(1,targetUnit);
          res=targetUnit.convert(finestDuration+targetToFinestFactor/2,finestUnit);
        }
      }
    }
    
    return res;
  }
  
  /**
   * Converts a source time duration in a specific source time unit to 
   * a target time duration in a specific target time unit and 
   * with the use of rounding. 
   * Rounding is applied if and only if the conversion results in a loss
   * of precision.
   * @param sourceDuration Source time duration.
   * @param sourceUnit Source time unit.
   * @param targetUnit Target time unit.
   * @param roundingMode RoundingMode.
   * @return Target time duration.
   */
  public static long convert(long sourceDuration,
                             TimeUnit sourceUnit,
                             TimeUnit targetUnit,
                             RoundingMode roundingMode)
  {
    long res=0;
    
    {
      if (roundingMode==null)
      {
        String message=
          "Failure to convert; rounding mode must be set!";
        throw new IllegalArgumentException(message);
      }
      else
      {
        switch (roundingMode)
        {
          case FLOOR:
          {
            res=convert_FLOOR(sourceDuration,sourceUnit,targetUnit);
            break;
          }
        
          case CEILING:
          {
            res=convert_CEILING(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          case UP:
          {
            res=convert_UP(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          case DOWN:
          {
            res=convert_DOWN(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          case HALF_UP:
          {
            res=convert_HALF_UP(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          case HALF_DOWN:
          {
            res=convert_HALF_DOWN(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          case HALF_EVEN:
          {
            res=convert_HALF_EVEN(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          case UNNECESSARY:
          {
            res=convert_UNNECESSARY(sourceDuration,sourceUnit,targetUnit);
            break;
          }
          
          default:
          {
            String message=
              "Failure to convert; rounding mode \""+
              roundingMode.name()+
              "\" can not be recognised!";
            throw new IllegalArgumentException(message);
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_FLOOR(long sourceDuration,
                                    TimeUnit sourceUnit,
                                    TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_CEILING(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just truncate!
          }
          else
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //Floor; just truncate!
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_CEILING(long sourceDuration,
                                      TimeUnit sourceUnit,
                                      TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_FLOOR(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
            res=targetUnit.convert(sourceDuration+(targetToSourceFactor-1),sourceUnit);  //Ceil!
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_UP(long sourceDuration,
                                 TimeUnit sourceUnit,
                                 TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_UP(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
            res=targetUnit.convert(sourceDuration+(targetToSourceFactor-1),sourceUnit);  //Up!
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_DOWN(long sourceDuration,
                                   TimeUnit sourceUnit,
                                   TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_DOWN(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //Down; just truncate!
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_HALF_UP(long sourceDuration,
                                      TimeUnit sourceUnit,
                                      TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_HALF_UP(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
            res=targetUnit.convert(sourceDuration+targetToSourceFactor/2,sourceUnit);  //Half-up!
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_HALF_DOWN(long sourceDuration,
                                        TimeUnit sourceUnit,
                                        TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_HALF_DOWN(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
            res=targetUnit.convert(sourceDuration+(targetToSourceFactor/2-1),sourceUnit);  //Half-down!
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_HALF_EVEN(long sourceDuration,
                                        TimeUnit sourceUnit,
                                        TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_HALF_EVEN(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            long targetUnit_truncate=targetUnit.convert(sourceDuration,sourceUnit);  //Truncate!

            if ((targetUnit_truncate&1L)==0L)  //if truncated integer part is even ...
            {
              res=targetUnit_truncate;  //Round down!
            }
            else
            {
              long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
              res=targetUnit.convert(sourceDuration+targetToSourceFactor/2,sourceUnit);  //Rounding up!
            }
          }
        }
      }
    }
    
    return res;
  }

  /**
   * 
   */
  private static long convert_UNNECESSARY(long sourceDuration,
                                          TimeUnit sourceUnit,
                                          TimeUnit targetUnit)
  {
    long res=0;
    
    {
      if (sourceUnit==targetUnit)
      {
        res=sourceDuration;  //No rounding!
      }
      else
      {
        if (sourceDuration<0)
        {
          res=-convert_UNNECESSARY(-sourceDuration,sourceUnit,targetUnit);
        }
        else
        {
          int order=targetUnit.compareTo(sourceUnit);
      
          if (order<=0)
          {
            res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
          }
          else
          {
            long targetDuration=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just truncate!
            long sourceDuration1=sourceUnit.convert(targetDuration,targetUnit);  //No rounding, just convert by scaling up!

            if (sourceDuration!=sourceDuration1)
            {
              throw new ArithmeticException();
            }
            
            res=targetDuration;
          }
        }
      }
    }
    
    return res;
  }
}

/******** "TimeUnitUtility.java" **********************************************/ 
