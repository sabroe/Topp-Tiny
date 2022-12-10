package com.yelstream.topp.util.format;

import com.yelstream.topp.util.regex.Patterns;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test suite for {@code com.yelstream.topp.util.format.ReplacerTest}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-08
 */
class ReplacerTest {
    /**
     * Test replace with matching argument-names, non-matching arguments-names, and escaped argument-names.
     */
    @Test
    void replaceWithMatchingAndNonMatchingAndEscapedArgumentNames() {
        final List<Match> matched=new ArrayList<>();
        final List<Replace> replaced=new ArrayList<>();
        final StringBuilder preformatted=new StringBuilder();

        Replacer.Builder builder=Replacer.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        builder.onPreformattedConsumer(preformatted::append);
        Replacer replacer=builder.build();

        String format="${time}-yyy-${space}-zzz-${science}-aaa-$${nomatch}-bbb";

        String formatted = replacer.replace(format, Map.of("time", 100, "space", 200));
        Assertions.assertEquals("100-yyy-200-zzz-${science}-aaa-${nomatch}-bbb",formatted);

        Assertions.assertEquals(List.of("${time}","${space}","${science}"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","science"),matched.stream().map(Match::getKey).toList());
        Assertions.assertEquals(List.of("100","200","${science}"),replaced.stream().map(Replace::getReplacement).toList());
        Assertions.assertEquals("100-yyy-200-zzz-${science}-aaa-$${nomatch}-bbb",preformatted.toString());
    }

    /**
     * Test replace with matching argument-names, non-matching arguments-names, and escaped argument-names
     * in non-default formatter pattern.
     */
    @Test
    void replaceWithMatchingAndNonMatchingAndEscapedArgumentNamesInFormatPattern() {
        final List<Match> matched=new ArrayList<>();
        final List<Replace> replaced=new ArrayList<>();
        final StringBuilder preformatted=new StringBuilder();

        Replacer.Builder builder=Replacer.builder();
        builder.parameterPattern(Patterns.createFormatterParameterPattern()).build();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        builder.onPreformattedConsumer(preformatted::append);
        Replacer replacer=builder.build();

        String format = "%time$s-yyy-%space$s-zzz-%science$s-aaa-%%nomatch$s-bbb";

        String formatted = replacer.replace(format, Map.of("time", 100, "space", 200));
        Assertions.assertEquals("100s-yyy-200s-zzz-%science$s-aaa-%%nomatch$s-bbb",formatted);
        Assertions.assertEquals(List.of("%time$","%space$","%science$"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","science"),matched.stream().map(Match::getKey).toList());
        Assertions.assertEquals(List.of("100","200","%science$"),replaced.stream().map(Replace::getReplacement).toList());
        Assertions.assertEquals("100s-yyy-200s-zzz-%science$s-aaa-%%nomatch$s-bbb",preformatted.toString());
    }

    /**
     * Test replace with repeated argument-names.
     */
    @Test
    void replaceWithRepeatedArgumentNames() {
        List<Match> matched=new ArrayList<>();
        List<Replace> replaced=new ArrayList<>();

        Replacer.Builder builder=Replacer.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        Replacer replacer=builder.build();

        String format="${time}-${space}-${time}-${space}";

        String formatted=replacer.replace(format,Map.of("time",100,"space",200));
        Assertions.assertEquals("100-200-100-200",formatted);
        Assertions.assertEquals(List.of("${time}","${space}","${time}","${space}"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","time","space"),matched.stream().map(Match::getKey).toList());
        Assertions.assertEquals(List.of("100","200","100","200"),replaced.stream().map(Replace::getReplacement).toList());
    }

    /**
     * Test replace with no matching argument-names.
     */
    @Test
    void replaceWithNoMatchingArgumentNames() {
        Replacer.Builder builder=Replacer.builder();
        Replacer replacer=builder.build();
        {
            String formatted=replacer.replace("${xxx}-$${yyy}",Map.of());
            Assertions.assertEquals("${xxx}-${yyy}",formatted);
        }
        {
            String formatted=replacer.replace("${xxx}-${yyy}",Map.of());
            Assertions.assertEquals("${xxx}-${yyy}",formatted);
        }
    }

    /**
     * Test replace with matching argument-names.
     */
    @Test
    void replaceWithMatchingArgumentNames() {
        Replacer.Builder builder=Replacer.builder();
        Replacer replacer=builder.build();
        {
            String formatted=replacer.replace("${xxx}-$${yyy}",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("aaa-${yyy}",formatted);
        }
        {
            String formatted=replacer.replace("${xxx}-${yyy}",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("aaa-bbb",formatted);
        }
    }

    /**
     * Test format with and without escaped arguments both at index 0 and at index >0.
     */
    @Test
    void replaceWithVariousEscapes() {
        Replacer.Builder builder=Replacer.builder();
        Replacer replacer=builder.build();
        {
            String formatted=replacer.replace("$${xxx}-$${yyy}",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("${xxx}-${yyy}",formatted);
        }
        {
            String formatted=replacer.replace("${xxx}-${yyy}",Map.of("xxx","aaa","yyy","bbb"));
            Assertions.assertEquals("aaa-bbb",formatted);
        }
    }
}
