package com.yelstream.topp.util.regex;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matching of a pattern in an append-and-replace loop.
 * <p>
 *   This may be described as {@link Matcher#appendReplacement(StringBuilder,String)}.
 * </p>
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-11
 */
@AllArgsConstructor
@Builder(builderClassName="Builder",toBuilder=true)
public class MatcherLoop {
    /**
     * Pattern to match.
     */
    private final Pattern pattern;

    /**
     * Format to match pattern within.
     */
    private final String format;

    /**
     * Indicates, if the replacement text is to be taken literal.
     */
    @lombok.Builder.Default
    private final boolean literalReplacement=true;

    /**
     * Runs the main matcher loop.
     * <p>
     * Note that this runs the standard matching loop:
     * </p>
     * <pre>
     *   Matcher matcher=Pattern.compile(...).matcher(...);
     *   while (matcher.find()) {
     *     ...
     *     matcher.appendReplacement(...);
     *   }
     *   matcher.appendTail(...);
     * </pre>
     * @param matcherToReplacement Mapping of a match to a replacement text.
     * @return Resulting formatting.
     */
    public String run(Function<Matcher,String> matcherToReplacement) {
        StringBuilder formatted=new StringBuilder();
        Matcher matcher=pattern.matcher(format);
        while (matcher.find()) {
            String replacement=matcherToReplacement.apply(matcher);
            if (literalReplacement) {
                replacement=Matchers.createLiteralReplacement(replacement);
            }
            matcher.appendReplacement(formatted,replacement);
        }
        matcher.appendTail(formatted);
        return formatted.toString();
    }
}
