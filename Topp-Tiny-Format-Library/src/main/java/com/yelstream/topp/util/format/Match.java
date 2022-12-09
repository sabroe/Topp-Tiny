package com.yelstream.topp.util.format;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Consumer;

/**
 * Matched pattern and key inside a string.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-07
 */
@AllArgsConstructor
@Getter
public class Match {
    /**
     * Matched pattern like {@code ${...}} or {@code %...$}.
     */
    private final String pattern;

    /**
     * Matched key inside the pattern like the dots {@code ...} inside a pattern like {@code ${...}} or {@code %...$}.
     */
    private final String key;

    public static void registerMatch(List<Consumer<Match>> onMatchConsumers,
                                     String pattern,
                                     String key) {
        if (onMatchConsumers!=null) {
            Match match=new Match(pattern,key);
            onMatchConsumers.forEach(consumer->consumer.accept(match));
        }
    }
}
