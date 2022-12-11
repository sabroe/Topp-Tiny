package com.yelstream.topp.util.management;

import com.yelstream.topp.util.nio.PathUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test suite for {@code com.yelstream.topp.util.management.HeapUtility}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-03
 */
class HeapUtilityTest {
    @Test
    void createHeapDumpInSpecificDirectory() throws IOException {
        Path directory=Paths.get("build/tmp/test/dump");
        Files.deleteIfExists(directory);
        Assertions.assertFalse(Files.exists(directory));

        String fileNamePrefix="aaa";
        HeapDumpSpecification specification=HeapDumpSpecification.of(directory,fileNamePrefix);

        Path heapDump1File=null;
        Path heapDump2File=null;
        try {
            heapDump1File=HeapUtility.dump(specification,"name-a");
            heapDump2File=HeapUtility.dump(specification,"name-b");

            Assertions.assertTrue(Files.exists(heapDump1File));
            Assertions.assertEquals(Paths.get("build/tmp/test/dump/aaa-name-a.hprof"),heapDump1File);
            Assertions.assertTrue(Files.exists(heapDump2File));
            Assertions.assertEquals(Paths.get("build/tmp/test/dump/aaa-name-b.hprof"),heapDump2File);
        } finally {
            PathUtility.deleteIfExists(heapDump1File);
            PathUtility.deleteIfExists(heapDump2File);
        }
    }

    @Test
    void createHeapDumpAsSimpleAsPossible() throws IOException {
        HeapDumpSpecification specification=HeapDumpSpecification.of();

        Path heapDumpFile=null;
        try {
            heapDumpFile=HeapUtility.dump(specification,"name");

            Assertions.assertTrue(Files.exists(heapDumpFile));
            Assertions.assertEquals(Paths.get("./heapdump-name.hprof"),heapDumpFile);
        } finally {
            PathUtility.deleteIfExists(heapDumpFile);
        }
    }
}
