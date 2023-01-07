package com.yelstream.topp.util.format;

import com.yelstream.topp.util.regex.MatcherLoop;
import com.yelstream.topp.util.regex.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Replaces named variables within a string.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-07
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName="Builder",toBuilder=true)
public class Replacer {
    /**
     * Pattern for matching parameters.
     */
    @lombok.Builder.Default
    private final Pattern parameterPattern=Patterns.createVariableParameterPattern();

    /**
     * Default format parameter.
     */
    @lombok.Builder.Default
    private final String defaultFormatParameter=null;

    /**
     * Consumers to notify about a matched pattern.
     */
    @Singular
    private List<Consumer<Match>> onMatchConsumers;

    /**
     * Consumers to notify about a replaced pattern.
     */
    @Singular
    private List<Consumer<Replace>> onReplaceConsumers;

    /**
     * Consumers to notify about the result of pre-formatting.
     */
    @Singular
    private List<Consumer<String>> onPreformattedConsumers;

    /**
     * Parameter.
     * @param name Name.
     * @param value Value.
     */
    public record Parameter(String name, Object value) {
    }

    /**
     * Creates a text by substituting parameters into a format.
     * @param format Format.
     * @param parameters Parameters.
     * @return Formatted text.
     */
    public String replace(String format,
                         List<Parameter> parameters) {
        Map<String,Object> parameterMap=parameters.stream().collect(Collectors.toMap(Parameter::name,Parameter::value));
        return replace(format,parameterMap);
    }

    /**
     * Creates a text by substituting parameters into a format.
     * @param format Format.
     * @param parameters Parameters.
     * @return Formatted text.
     */
    public String replace(String format,
                          Map<String,Object> parameters) {
        MatcherLoop matcherLoop=MatcherLoop.builder().pattern(parameterPattern).format(format).build();
        String preformatted=
            matcherLoop.run(matcher -> {
                String pattern=matcher.group(Patterns.PATTERN_GROUP_NAME);
                String key=matcher.group(Patterns.KEY_GROUP_NAME);
                Match.registerMatch(onMatchConsumers,pattern,key);
                String replacement=getReplacement(parameters,key,pattern);
                Replace.registerReplace(onReplaceConsumers,pattern,key,replacement);
                return replacement;
            });
        registerPreformatted(onPreformattedConsumers,preformatted);
        return preformatted.replace("$$","$");
    }

    /**
     * Gets the replacement for a matched pattern.
     * @param parameters Parameters.
     * @param key Matched key.
     * @param pattern Matched pattern.
     * @return Replacement.
     */
    private String getReplacement(Map<String,Object> parameters,
                                  String key,
                                  String pattern) {
        String replacement;
        if (!parameters.containsKey(key)) {
            if (defaultFormatParameter==null) {
                replacement=pattern;
            } else {
                replacement=defaultFormatParameter;
            }
        } else {
            replacement=parameters.get(key).toString();
        }
        return replacement;
    }

    /**
     * Register the result of pre-formatting by notifying all consumers.
     * @param onPreformattedConsumers Consumers to notify.
     * @param preformatted Preformatted format.
     */
    private static void registerPreformatted(List<Consumer<String>> onPreformattedConsumers,
                                             String preformatted) {
        if (onPreformattedConsumers!=null) {
            onPreformattedConsumers.forEach(consumer->consumer.accept(preformatted));
        }
    }
}
