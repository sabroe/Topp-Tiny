package com.yelstream.topp.util.tool;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Test suite for {@code com.yelstream.topp.tool.RandomContentGenerator}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-06
 */
class RandomContentGeneratorTest {
    @Test
    void replace() {
    }

    public static void main(String[] args) throws IOException {
/*
        createFileWithRandomContent(Paths.get("C:/temp/random-1.scratch"),1L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-10.scratch"),10L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-30.scratch"),30L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-50.scratch"),50L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-100.scratch"),100L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-200.scratch"),200L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-300.scratch"),300L*1024L*1024L);
        createFileWithRandomContent(Paths.get("C:/temp/random-500.scratch"),500L*1024L*1024L);
*/
//        createFileWithRandomContent(Paths.get("C:/temp/random-1000.scratch"),1000L*1024L*1024L);
        RandomContentGenerator contentGenerator=new RandomContentGenerator();
        contentGenerator.createContent(Paths.get("C:/temp/random-10.scratch"),10L*1024L*1024L);
    }

}
