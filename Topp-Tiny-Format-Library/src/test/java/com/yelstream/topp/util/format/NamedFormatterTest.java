package com.yelstream.topp.util.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Test suite for {@code com.yelstream.topp.util.format.NamedFormatterTest}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-08
 */
class NamedFormatterTest {
    /**
     * Test format with matching argument-names, non-matching arguments-names, and escaped argument-names.
     */
    @Test
    void formatWithMatchingAndNonMatchingAndEscapedArgumentNames() {
        final List<Match> matched=new ArrayList<>();
        final List<Replace> replaced=new ArrayList<>();
        final StringBuilder preformatted=new StringBuilder();
        final List<Object> formatArguments=new ArrayList<>();

        NamedFormatter.Builder builder=NamedFormatter.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        builder.onPreformattedConsumer((pf,fa) -> {
            preformatted.append(pf);
            formatArguments.addAll(fa);
        });
        NamedFormatter formatter=builder.build();

        String format="%time$s-yyy-%space$s-zzz-%science$s-aaa-%%nomatch$s-bbb";

        String formatted=formatter.format(format,Map.of("time",100,"space",200));
        Assertions.assertEquals("100-yyy-200-zzz-%science$s-aaa-%nomatch$s-bbb",formatted);
        Assertions.assertEquals(List.of("%time$","%space$","%science$"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","science"),matched.stream().map(Match::getKey).toList());
        Assertions.assertEquals(List.of("%1$","%2$","%%science$"),replaced.stream().map(Replace::getReplacement).toList());
        Assertions.assertEquals("%1$s-yyy-%2$s-zzz-%%science$s-aaa-%%nomatch$s-bbb",preformatted.toString());
        Assertions.assertEquals(List.of(100,200),formatArguments);
    }

    /**
     * Test format with repeated argument-names.
     */
    @Test
    void formatWithRepeatedArgumentNames() {
        final List<Match> matched=new ArrayList<>();
        final List<Replace> replaced=new ArrayList<>();
        final StringBuilder preformatted=new StringBuilder();
        final List<Object> formatArguments=new ArrayList<>();

        NamedFormatter.Builder builder=NamedFormatter.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        builder.onPreformattedConsumer((pf,fa) -> {
            preformatted.append(pf);
            formatArguments.addAll(fa);
        });
        NamedFormatter formatter=builder.build();

        String format="%time$s-%space$s-%time$s-%space$s";

        String formatted=formatter.format(format,Map.of("time",100,"space",200));
        Assertions.assertEquals("100-200-100-200",formatted);
        Assertions.assertEquals(List.of("%time$","%space$","%time$","%space$"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","time","space"),matched.stream().map(Match::getKey).toList());
        Assertions.assertEquals(List.of("%1$","%2$","%1$","%2$"),replaced.stream().map(Replace::getReplacement).toList());
        Assertions.assertEquals("%1$s-%2$s-%1$s-%2$s",preformatted.toString());
        Assertions.assertEquals(List.of(100,200),formatArguments);
    }

    /**
     * Test format with no matching argument-names.
     */
    @Test
    void formatWithNoMatchingArgumentNames() {
        NamedFormatter.Builder builder=NamedFormatter.builder();
        NamedFormatter formatter=builder.build();
        {
            String formatted=formatter.format("%xxx$s-%%yyy$s",Map.of());
            Assertions.assertEquals("%xxx$s-%yyy$s",formatted);
        }
        {
            String formatted=formatter.format("%xxx$s-%yyy$s",Map.of());
            Assertions.assertEquals("%xxx$s-%yyy$s",formatted);
        }
    }

    /**
     * Test format with matching argument-names.
     */
    @Test
    void formatWithMatchingArgumentNames() {
        NamedFormatter.Builder builder=NamedFormatter.builder();
        NamedFormatter formatter=builder.build();
        {
            String formatted=formatter.format("%xxx$s-%%yyy$s",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("aaa-%yyy$s",formatted);
        }
        {
            String formatted=formatter.format("%xxx$s-%yyy$s",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("aaa-bbb",formatted);
        }
    }

    /**
     * Test format with and without escaped arguments both at index 0 and at index >0.
     */
    @Test
    void formatWithVariousEscapes() {
        NamedFormatter.Builder builder=NamedFormatter.builder();
        NamedFormatter formatter=builder.build();
        {
            String formatted=formatter.format("%%xxx$s-%%yyy$s",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("%xxx$s-%yyy$s",formatted);
        }
        {
            String formatted=formatter.format("%xxx$s-%yyy$s",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("aaa-bbb",formatted);
        }
    }

    /**
     * Test format with the usage of a default format argument.
     */
    @Test
    void formatWithDefaultFormatArgument() {
        NamedFormatter.Builder builder=NamedFormatter.builder();
        builder.defaultFormatArgument("???");
        NamedFormatter formatter=builder.build();
        {
            String formatted=formatter.format("%xxx$s-%yyy$s-%zzz$s",Map.of("xxx","aaa"));
            Assertions.assertEquals("aaa-???-???",formatted);
        }
    }
}
