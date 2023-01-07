package com.yelstream.topp.util.management;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Test suite for {@code com.yelstream.topp.util.management.DefaultPathGenerator}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-03
 */
class DefaultPathGeneratorTest {
    @Test
    void createPathGeneratorWithPrefixAndSuffixAndExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="prefix";
        String fileNameSuffix="suffix";
        String fileNameExtension="bin";
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNameFormat(fileNamePrefix,fileNameSuffix,fileNameExtension).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/prefix-part-suffix.bin"),path);
    }

    @Test
    void createPathGeneratorWithNoFormatAndNoPrefix() {
        Path directory=Paths.get("/hello/here");
        String fileNameExtension="txt";
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNameExtension(fileNameExtension).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/part.txt"),path);
    }

    @Test
    void createPathGeneratorWithPrefixAndExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="aaa";
        String fileNameExtension="txt";
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).fileNameExtension(fileNameExtension).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/aaa-part.txt"),path);
    }

    @Test
    void createPathGeneratorWithPrefixAndExtensionAndSuffix() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="aaa";
        String fileNameExtension="txt";
        String fileNameSuffix="bbb";
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).fileNameExtension(fileNameExtension).fileNameSuffix(fileNameSuffix).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/aaa-part-bbb.txt"),path);
    }

    @Test
    void createPathGeneratorWithPrefixAndTimeAndExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="aaa";
        String filenameExtension="txt";
        LocalDateTime time=LocalDateTime.of(2022,12,4,2, 23,20,123456789);
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).fileNameExtension(filenameExtension).time(time).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/aaa-2022-12-04-022320-123-part.txt"),path);
    }

    @Test
    void createPathGeneratorWithPrefixAndTimeSupplierAndExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="aaa";
        String filenameExtension="txt";
        LocalDateTime time=LocalDateTime.of(2022,12,4,2, 23,20,123456789);
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).fileNameExtension(filenameExtension).timeSupplier(()->time).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/aaa-part-2022-12-04-022320-123.txt"),path);
    }

    @Test
    void createPathGeneratorWithTimeAndTimeSupplier() {
        Path directory = Paths.get("/hello/here");
        String fileNamePrefix = "aaa";
        String filenameExtension = "txt";
        LocalDateTime time = LocalDateTime.of(2022, 12, 4, 2, 23, 20, 123456789);
        DefaultPathGenerator.Builder builder=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).fileNameExtension(filenameExtension).time(time).timeSupplier(()->time);

        Assertions.assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void createPathGeneratorWithNoExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="aaa";
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).build();

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/aaa-part"),path);
    }

    @Test
    void createPathGeneratorWithFormat() {
        Path directory=Paths.get("/hello/here");
        String fileNameFormat="prefix-%s-suffix.ext";
        PathGenerator pathGenerator=DefaultPathGenerator.builder().directory(directory).fileNameFormat(fileNameFormat).build();

        Path path=pathGenerator.generate("infix");
        Assertions.assertEquals(Paths.get("/hello/here/prefix-infix-suffix.ext"),path);
    }

    /**
     * .
     */
    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(null, null, "extension"),
                Arguments.of(null, "suffix", null),
                Arguments.of(null, "suffix", "extension"),
                Arguments.of("prefix", null, null),
                Arguments.of("prefix", null, "extension"),
                Arguments.of("prefix", "suffix", null),
                Arguments.of("prefix", "suffix", "extension")
        );
    }

    @ParameterizedTest(name="{index}: prefix={0}, infix={1}, suffix={2}")
    @MethodSource("data")
    void createPathGeneratorWithFormatAndMore(String fileNamePrefix,
                                              String fileNameSuffix,
                                              String fileNameExtension) {
        Path directory=Paths.get("/hello/here");
        String fileNameFormat="prefix-%s-suffix.ext";
        DefaultPathGenerator.Builder builder=DefaultPathGenerator.builder().directory(directory).fileNameFormat(fileNameFormat).fileNamePrefix(fileNamePrefix).fileNameSuffix(fileNameSuffix).fileNameExtension(fileNameExtension);
        Assertions.assertThrows(IllegalArgumentException.class, builder::build);
    }
}
