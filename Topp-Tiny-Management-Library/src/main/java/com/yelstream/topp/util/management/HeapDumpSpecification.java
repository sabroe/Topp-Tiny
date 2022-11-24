package com.yelstream.topp.util.management;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

/**
 * Specification of how to create heap dumps.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-10-30
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class HeapDumpSpecification {
    private final PathGenerator pathGenerator;
    private final boolean live;

    /**
     * Creates a specification of how to generate a heap dump.
     * @param directory Directory to place heap dump files in.
     * @param fileNamePrefix Prefix for the names fof heap dump files.
     * @return Specification of how to generate a heap dump.
     */
    public static HeapDumpSpecification of(Path directory,
                                           String fileNamePrefix) {
        PathGenerator fileNameGenerator=PathGenerator.of(directory,fileNamePrefix,HeapUtility.HEAP_DUMP_FILE_NAME_EXTENSION);
        return new HeapDumpSpecification(fileNameGenerator,true);
    }
}
