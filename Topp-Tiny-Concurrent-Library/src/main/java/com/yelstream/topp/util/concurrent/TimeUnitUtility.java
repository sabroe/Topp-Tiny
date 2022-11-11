package com.yelstream.topp.util.concurrent;

import lombok.experimental.UtilityClass;

import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * Utility for manipulating instances of {@link TimeUnit}.
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-02-28
 */
@SuppressWarnings("unused")
@UtilityClass
public class TimeUnitUtility {
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
                                           TimeUnit targetUnit) {
        return convertWithRounding1(sourceDuration,
                                    sourceUnit,
                                    targetUnit);
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
                                            TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;
        } else {
            if (sourceDuration<0) {
                res=-convertWithRounding(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);  //Comparison is sound; units are declared in order of granularity with 'NANOSECONDS' first and 'DAYS' last!

                if (order<=0)  { //if the source has a coarser granularity than the target ...
                    res=targetUnit.convert(sourceDuration,sourceUnit);
                } else {
                    long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
                    res=targetUnit.convert(sourceDuration+targetToSourceFactor/2,sourceUnit);
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
                                            TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;
        } else {
            if (sourceDuration<0) {
                res=-convertWithRounding2(-sourceDuration,sourceUnit,targetUnit);
            } else {
                TimeUnit finestUnit=TimeUnit.NANOSECONDS;
                long finestDuration=finestUnit.convert(sourceDuration,sourceUnit);

                long targetToFinestFactor=finestUnit.convert(1,targetUnit);
                res=targetUnit.convert(finestDuration+targetToFinestFactor/2,finestUnit);
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
                               RoundingMode roundingMode) {
        long res;

        if (roundingMode==null) {
            throw new IllegalArgumentException("Failure to convert; rounding mode must be set!");
        } else {
            switch (roundingMode) {
                case FLOOR: {
                    res= convertFloor(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case CEILING: {
                    res= convertCeiling(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case UP: {
                    res= convertUp(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case DOWN: {
                    res= convertDown(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case HALF_UP: {
                    res= convertHalfUp(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case HALF_DOWN: {
                    res= convertHalfDown(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case HALF_EVEN: {
                    res= convertHalfEven(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                case UNNECESSARY: {
                    res= convertUnnecessary(sourceDuration,sourceUnit,targetUnit);
                    break;
                }

                default: {
                    throw new IllegalArgumentException(String.format("Failure to convert; rounding mode %s can not be recognised!",roundingMode.name()));
                }
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertFloor(long sourceDuration,
                                     TimeUnit sourceUnit,
                                     TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertCeiling(-sourceDuration,sourceUnit,targetUnit);
            } else {
                res=targetUnit.convert(sourceDuration,sourceUnit);  //Floor; just truncate!
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertCeiling(long sourceDuration,
                                       TimeUnit sourceUnit,
                                       TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertFloor(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);
                if (order<=0) {
                    res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
                } else {
                    long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
                    res=targetUnit.convert(sourceDuration+(targetToSourceFactor-1),sourceUnit);  //Ceil!
                }
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertUp(long sourceDuration,
                                  TimeUnit sourceUnit,
                                  TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertUp(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);

                if (order<=0) {
                    res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
                } else {
                    long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
                    res=targetUnit.convert(sourceDuration+(targetToSourceFactor-1),sourceUnit);  //Up!
                }
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertDown(long sourceDuration,
                                    TimeUnit sourceUnit,
                                    TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertDown(-sourceDuration,sourceUnit,targetUnit);
            } else {
                res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just truncate!
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertHalfUp(long sourceDuration,
                                      TimeUnit sourceUnit,
                                      TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertHalfUp(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);

                if (order<=0) {
                    res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
                } else {
                    long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
                    res=targetUnit.convert(sourceDuration+targetToSourceFactor/2,sourceUnit);  //Half-up!
                }
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertHalfDown(long sourceDuration,
                                        TimeUnit sourceUnit,
                                        TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertHalfDown(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);
                if (order<=0) {
                    res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
                } else {
                    long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
                    res=targetUnit.convert(sourceDuration+(targetToSourceFactor/2-1),sourceUnit);  //Half-down!
                }
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertHalfEven(long sourceDuration,
                                        TimeUnit sourceUnit,
                                        TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertHalfEven(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);
                if (order<=0) {
                    res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
                } else {
                    long targetUnitTruncate=targetUnit.convert(sourceDuration,sourceUnit);  //Truncate!

                    if ((targetUnitTruncate&1L)==0L) { //if truncated integer part is even ...
                        res=targetUnitTruncate;  //Round down!
                    } else {
                        long targetToSourceFactor=sourceUnit.convert(1,targetUnit);
                        res=targetUnit.convert(sourceDuration+targetToSourceFactor/2,sourceUnit);  //Rounding up!
                    }
                }
            }
        }

        return res;
    }

    /**
     * .
     */
    private static long convertUnnecessary(long sourceDuration,
                                           TimeUnit sourceUnit,
                                           TimeUnit targetUnit) {
        long res;

        if (sourceUnit==targetUnit) {
            res=sourceDuration;  //No rounding!
        } else {
            if (sourceDuration<0) {
                res=-convertUnnecessary(-sourceDuration,sourceUnit,targetUnit);
            } else {
                int order=targetUnit.compareTo(sourceUnit);

                if (order<=0) {
                    res=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just convert by scaling up!
                } else {
                    long targetDuration=targetUnit.convert(sourceDuration,sourceUnit);  //No rounding, just truncate!
                    long sourceDuration1=sourceUnit.convert(targetDuration,targetUnit);  //No rounding, just convert by scaling up!

                    if (sourceDuration!=sourceDuration1) {
                        throw new ArithmeticException();
                    }

                    res=targetDuration;
                }
            }
        }

        return res;
    }
}
