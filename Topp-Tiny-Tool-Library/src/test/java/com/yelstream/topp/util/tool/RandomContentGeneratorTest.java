package com.yelstream.topp.util.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test suite for {@code com.yelstream.topp.tool.RandomContentGenerator}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-06
 */
class RandomContentGeneratorTest {
    /**
     * Tests the method {@link RandomContentGenerator#createContent(Path, long)}.
     * @throws IOException Thrown in case of I/O error.
     */
    @Test
    void createContent10MiBFile() throws IOException {
        RandomContentGenerator contentGenerator=new RandomContentGenerator();

        Path dirPath=Paths.get("build/temp");
        Files.createDirectories(dirPath);
        Path filePath=dirPath.resolve("random-10.scratch");
        Files.deleteIfExists(filePath);

        contentGenerator.createContent(filePath,10L*1024L*1024L);

        Assertions.assertTrue(Files.exists(filePath));
        Assertions.assertEquals(10L*1024L*1024L,Files.size(filePath));

        Assertions.assertThrows(IOException.class,()->contentGenerator.createContent(filePath,10L*1024L*1024L));
    }
}
