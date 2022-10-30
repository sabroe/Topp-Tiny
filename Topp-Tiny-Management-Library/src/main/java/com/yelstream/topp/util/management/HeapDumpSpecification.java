package com.yelstream.topp.util.management;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

/**
 * Specification of how to create heap dumps.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-10-30
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class HeapDumpSpecification {
    private final PathGenerator pathGenerator;
    private final boolean live;

    @SuppressWarnings("unused")
    public static HeapDumpSpecification of(Path directory,
                                           String fileNamePrefix) {
        PathGenerator fileNameGenerator=PathGenerator.of(directory,fileNamePrefix,HeapUtility.HEAP_DUMP_FILE_NAME_EXTENSION);
        return new HeapDumpSpecification(fileNameGenerator,true);
    }
}
