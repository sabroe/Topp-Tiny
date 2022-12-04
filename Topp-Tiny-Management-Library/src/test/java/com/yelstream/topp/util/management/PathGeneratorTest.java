package com.yelstream.topp.util.management;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.UnaryOperator;

/**
 * Test suite for {@code com.yelstream.topp.util.management.PathGenerator}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-03
 */
class PathGeneratorTest {
/*
    @Test
    void createPathGeneratorWithPrefixAndSuffixAndExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="prefix";
        String fileNameSuffix="suffix";
        String filenameExtension="bin";
        PathGenerator pathGenerator=PathGenerator.of(directory,fileNamePrefix,fileNameSuffix,filenameExtension);

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/prefix-part-suffix.bin"),path);
    }

    @Test
    void createPathGeneratorWithPrefixAndExtension() {
        Path directory=Paths.get("/hello/here");
        String fileNamePrefix="aaa";
        String filenameExtension="txt";
        PathGenerator pathGenerator=PathGenerator.of(directory,fileNamePrefix,filenameExtension);

        Path path=pathGenerator.generate("part");
        Assertions.assertEquals(Paths.get("/hello/here/aaa-part.txt"),path);
    }

    @Test
    void createPathGeneratorWithFileNameFormat() {
        Path directory=Paths.get("/hello/here");
        String fileNameFormat="prefix-%s-suffix.ext";
        PathGenerator pathGenerator=PathGenerator.of(directory,fileNameFormat);

        Path path=pathGenerator.generate("infix");
        Assertions.assertEquals(Paths.get("/hello/here/prefix-infix-suffix.ext"),path);
    }

    @Test
    void createPathGeneratorWithUnaryOperator() {
        Path directory=Paths.get("/hello/here");
        UnaryOperator<String> partNameToFullFileName=part->String.format("prefix-%s-suffix.ext",part);
        PathGenerator pathGenerator=PathGenerator.of(directory,partNameToFullFileName);

        Path path=pathGenerator.generate("infix");
        Assertions.assertEquals(Paths.get("/hello/here/prefix-infix-suffix.ext"),path);
    }
*/
}