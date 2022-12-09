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
    @Test
    void replace1() {
        List<Match> matched=new ArrayList<>();
        List<Replace> replaced=new ArrayList<>();

        Replacer.Builder builder=Replacer.builder();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        Replacer replacer=builder.build();

        String format="${time}-yyy-${space}-zzz-${science}-aaa-$${nomatch}-bbb";

        String formatted = replacer.replace(format, Map.of("time", 100, "space", 200));
        Assertions.assertEquals("100-yyy-200-zzz-${science}-aaa-$${nomatch}-bbb",formatted);

        Assertions.assertEquals(List.of("${time}","${space}","${science}"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","science"),matched.stream().map(Match::getKey).toList());
    }

    @Test
    void replace2() {
        List<Match> matched=new ArrayList<>();
        List<Replace> replaced=new ArrayList<>();

        Replacer.Builder builder=Replacer.builder();
        builder.parameterPattern(Patterns.createFormatterParameterPattern()).build();
        builder.onMatchConsumer(matched::add);
        builder.onReplaceConsumer(replaced::add);
        Replacer replacer=builder.build();

        String format = "%time$s-yyy-%space$s-zzz-%science$s-aaa-%%nomatch$s-bbb";

        String formatted = replacer.replace(format, Map.of("time", 100, "space", 200));
        Assertions.assertEquals("100s-yyy-200s-zzz-%science$s-aaa-%%nomatch$s-bbb",formatted);
        Assertions.assertEquals(List.of("%time$","%space$","%science$"),matched.stream().map(Match::getPattern).toList());
        Assertions.assertEquals(List.of("time","space","science"),matched.stream().map(Match::getKey).toList());
    }
}
