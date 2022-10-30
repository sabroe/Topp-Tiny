package com.yelstream.topp.util.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Converts {@code long} time duration values by truncation.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-02-28
 */
public class TruncateTimeUnitConverter implements TimeUnitConverter
{
    @Override
    public long convert(long sourceDuration,
                        TimeUnit sourceUnit,
                        TimeUnit targetUnit) {
      return targetUnit.convert(sourceDuration,sourceUnit);
    }
}
