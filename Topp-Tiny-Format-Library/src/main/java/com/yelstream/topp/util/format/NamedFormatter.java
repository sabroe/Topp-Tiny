package com.yelstream.topp.util.format;

import com.yelstream.topp.util.collection.Maps;
import com.yelstream.topp.util.regex.MatcherLoop;
import com.yelstream.topp.util.regex.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;

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
public class NamedFormatter {
    @lombok.Builder.Default
    private final Locale locale=null;

    /**
     * Pattern for matching parameters.
     */
    @lombok.Builder.Default
    private final Pattern argumentPattern=Patterns.createFormatterParameterPattern();

    /**
     * Default format argument.
     */
    @lombok.Builder.Default
    private final Object defaultFormatArgument=null;

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
    private List<BiConsumer<String,List<Object>>> onPreformattedConsumers;

    /**
     * Format argument.
     * @param name Name.
     * @param value Value.
     */
    public record Argument(String name, Object value) {
    }

    /**
     * Creates a text by substituting arguments into a format.
     * @param format Format.
     * @param arguments Arguments.
     * @return Formatted text.
     */
    public String format(String format,
                         List<Argument> arguments) {
        Map<String,Object> argumentMap=new HashMap<>();
        arguments.forEach(argument -> Maps.putFromScratch(argumentMap,argument.name,argument.value));
        return format(format,argumentMap);
    }

    /**
     * Creates a text by substituting arguments into a format.
     * @param format Format.
     * @param arguments Arguments.
     * @return Formatted text.
     */
    public String format(String format,
                         Map<String,Object> arguments) {
        List<Object> formatArguments=new ArrayList<>();
        MatcherLoop matcherLoop=MatcherLoop.builder().pattern(argumentPattern).format(format).build();
        String preformatted=
            matcherLoop.run(matcher -> {
                String pattern=matcher.group(Patterns.PATTERN_GROUP_NAME);
                String key=matcher.group(Patterns.KEY_GROUP_NAME);
                Match.registerMatch(onMatchConsumers,pattern,key);
                String replacement=getReplacement(arguments,key,pattern,formatArguments);
                Replace.registerReplace(onReplaceConsumers,pattern,key,replacement);
                return replacement;
            });
        registerPreformatted(onPreformattedConsumers,preformatted,formatArguments);
        return String.format(locale,preformatted,formatArguments.toArray());
    }

    /**
     * Gets the replacement for a matched pattern.
     * @param arguments Arguments.
     * @param key Matched key.
     * @param pattern Matched pattern.
     * @param formatArguments Format arguments.
     * @return Replacement.
     */
    private String getReplacement(Map<String,Object> arguments,
                                  String key,
                                  String pattern,
                                  List<Object> formatArguments) {
        String replacement;
        Object formatArgument=getFormatArgument(arguments,key);
        if (formatArgument==null) {
            replacement=createLiteral(pattern);
        } else {
            int index=formatArguments.indexOf(formatArgument);
            if (index==-1) {
                index=formatArguments.size();
                formatArguments.add(formatArgument);
            }
            replacement="%"+(1+index)+"$";
        }
        return replacement;
    }

    /**
     * Gets the argument to use for the actual formatting with {@link java.util.Formatter}.
     * @param arguments Arguments.
     * @param key Matching key.
     * @return Argument for formatting.
     */
    private Object getFormatArgument(Map<String,Object> arguments,
                                     String key) {
        Object formatArgument;
        if (!arguments.containsKey(key)) {
            if (defaultFormatArgument==null) {
                formatArgument=null;
            } else {
                formatArgument=defaultFormatArgument;
            }
        } else {
            formatArgument=arguments.get(key);
        }
        return formatArgument;
    }

    /**
     * Creates a token to be literal when placed inside a format used for {@link java.util.Formatter}.
     * @param token Token to make literal.
     * @return Literal token.
     */
    private static String createLiteral(String token) {
        return token.replace("%","%%");
    }

    /**
     * Register the result of pre-formatting by notifying all consumers.
     * @param onPreformattedConsumers Consumers to notify.
     * @param preformatted Preformatted format.
     * @param formatArguments Format arguments.
     */
    private static void registerPreformatted(List<BiConsumer<String,List<Object>>> onPreformattedConsumers,
                                             String preformatted,
                                             List<Object> formatArguments) {
        if (onPreformattedConsumers!=null) {
            onPreformattedConsumers.forEach(consumer->consumer.accept(preformatted,formatArguments));
        }
    }
}
