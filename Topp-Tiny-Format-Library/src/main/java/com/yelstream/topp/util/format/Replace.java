package com.yelstream.topp.util.format;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Consumer;

/**
 * Matched pattern and key inside a string together with the replacement of the pattern.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-07
 */
@AllArgsConstructor
@Getter
public class Replace {
    /**
     * Matched pattern like {@code ${...}} or {@code %...$}.
     */
    private final String pattern;

    /**
     * Matched key inside the pattern like the dots {@code ...} inside a pattern like {@code ${...}} or {@code %...$}.
     */
    private final String key;

    /**
     * Replacement for the matched pattern.
     */
    private final String replacement;

    /**
     * Register a replaced pattern by notifying all consumers.
     * @param onReplaceConsumers Consumers to notify.
     * @param pattern Pattern text.
     * @param key Key text.
     * @param replacement Replacement text.
     */
    public static void registerReplace(List<Consumer<Replace>> onReplaceConsumers,
                                       String pattern,
                                       String key,
                                       String replacement) {
        if (onReplaceConsumers!=null) {
            Replace replace=new Replace(pattern,key,replacement);
            onReplaceConsumers.forEach(consumer->consumer.accept(replace));
        }
    }
}
