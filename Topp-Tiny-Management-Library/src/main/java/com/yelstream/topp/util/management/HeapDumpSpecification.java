package com.yelstream.topp.util.management;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.function.Supplier;

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
     * Creates a specification.
     * @param directory Directory to place heap dump files in.
     * @param fileNamePrefix Prefix for the names fof heap dump files.
     * @return Specification.
     */
    public static HeapDumpSpecification of(Path directory,
                                           String fileNamePrefix) {
        PathGenerator fileNameGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).fileNameExtension(HeapUtility.HEAP_DUMP_FILE_NAME_EXTENSION).build();
        return new HeapDumpSpecification(fileNameGenerator,true);
    }

    /**
     * Creates a specification.
     * @param directory Directory to place heap dump files in.
     * @param fileNamePrefix Prefix for the names fof heap dump files.
     * @param temporalAccessor Fixed time formatted into file name.
     * @return Specification.
     */
    public static HeapDumpSpecification of(Path directory,
                                           String fileNamePrefix,
                                           TemporalAccessor temporalAccessor) {
        PathGenerator fileNameGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).time(temporalAccessor).fileNameExtension(HeapUtility.HEAP_DUMP_FILE_NAME_EXTENSION).build();
        return new HeapDumpSpecification(fileNameGenerator,true);
    }

    /**
     * Creates a specification.
     * @param directory Directory to place heap dump files in.
     * @param fileNamePrefix Prefix for the names fof heap dump files.
     * @param temporalAccessorSupplier Supplier of time formatted into file name.
     * @return Specification.
     */
    public static HeapDumpSpecification of(Path directory,
                                           String fileNamePrefix,
                                           Supplier<TemporalAccessor> temporalAccessorSupplier) {
        PathGenerator fileNameGenerator=DefaultPathGenerator.builder().directory(directory).fileNamePrefix(fileNamePrefix).timeSupplier(temporalAccessorSupplier).fileNameExtension(HeapUtility.HEAP_DUMP_FILE_NAME_EXTENSION).build();
        return new HeapDumpSpecification(fileNameGenerator,true);
    }

    /**
     * Creates a specification.
     * @param fileNamePrefix Prefix for the names fof heap dump files.
     * @return Specification.
     */
    public static HeapDumpSpecification of(String fileNamePrefix) {
        return of(Paths.get("."),fileNamePrefix, Instant.now());
    }

    /**
     * Creates a specification.
     * @return Specification.
     */
    public static HeapDumpSpecification of() {
        return of(Paths.get("."),"heapdump");
    }
}
