package com.yelstream.topp.util.concurrent;

import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * Converts {@code long} time duration values by rounding.
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-02-28
 */
public class RoundingModeTimeUnitConverter implements TimeUnitConverter {
    public RoundingModeTimeUnitConverter(RoundingMode roundingMode) {
        this.roundingMode=roundingMode;
    }
  
    protected final RoundingMode roundingMode;
  
    @Override
    public long convert(long sourceDuration,
                        TimeUnit sourceUnit,
                        TimeUnit targetUnit) {
        return TimeUnitUtility.convert(sourceDuration,sourceUnit,targetUnit,roundingMode);
    }
}
