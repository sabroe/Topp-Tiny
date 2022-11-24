package com.yelstream.topp.util.concurrent;

import lombok.AllArgsConstructor;

import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * Converts {@code long} time duration values by rounding.
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-02-28
 */
@AllArgsConstructor
public class RoundingModeTimeUnitConverter implements TimeUnitConverter {
    /**
     * Rounding mode.
     */
    protected final RoundingMode roundingMode;
  
    @Override
    public long convert(long sourceDuration,
                        TimeUnit sourceUnit,
                        TimeUnit targetUnit) {
        return TimeUnitUtility.convert(sourceDuration,sourceUnit,targetUnit,roundingMode);
    }
}
