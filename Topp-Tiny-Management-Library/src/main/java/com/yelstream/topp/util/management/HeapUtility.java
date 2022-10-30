package com.yelstream.topp.util.management;

import com.sun.management.HotSpotDiagnosticMXBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import javax.management.MBeanServer;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;

/**
 * Utilities addressing the heap space of the Java VM.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-10-30
 */
@SuppressWarnings("unused")
@UtilityClass
public final class HeapUtility {

    /**
     * File name extension of heap dumps.
     */
    public static final String HEAP_DUMP_FILE_NAME_EXTENSION="hprof";

    /**
     * Specification of hos to create heap dumps.
     */
    @AllArgsConstructor
    @Getter
    public static class DumpSpecification {
        private final PathGenerator pathGenerator;
        private final boolean live;

        @SuppressWarnings("unused")
        public static DumpSpecification of(Path directory,
                                           String fileNamePrefix) {
            PathGenerator fileNameGenerator= PathGenerator.of(directory,fileNamePrefix,HEAP_DUMP_FILE_NAME_EXTENSION);
            return new DumpSpecification(fileNameGenerator,true);
        }
    }

    /**
     * Creates a heap dump.
     * @param fileName Name of file containing heap dump.
     *                 This must use the file name extension {@link #HEAP_DUMP_FILE_NAME_EXTENSION}.
     * @param live Indicates, if the objects contained in the heap dump are the live ones only.
     * @throws IOException Thrown in case of I/O error.
     */
    public static void dump(String fileName,
                            boolean live) throws IOException {
        MBeanServer server=ManagementFactory.getPlatformMBeanServer();
        HotSpotDiagnosticMXBean bean=ManagementFactory.newPlatformMXBeanProxy(server,"com.sun.management:type=HotSpotDiagnostic",HotSpotDiagnosticMXBean.class);
        bean.dumpHeap(fileName,live);
    }

    /**
     * Creates a heap dump.
     * @param file File containing heap dump.
     * @param live Indicates, if the objects contained in the heap dump are the live ones only.
     * @throws IOException Thrown in case of I/O error.
     */
    public static void dump(Path file,
                            boolean live) throws IOException {
        String fileName=file.toAbsolutePath().toString();
        dump(fileName,live);
    }

    /**
     * Creates a heap dump.
     * @param specification Specification of how to create the heap dump.
     * @param name Name of heap dump.
     *             This becomes part of the name of the file containing the heap dump.
     * @throws IOException Thrown in case of I/O error.
     */
    public static void dump(DumpSpecification specification,
                            String name) throws IOException {
        dump(specification.pathGenerator.generate(name),specification.isLive());
    }
}
