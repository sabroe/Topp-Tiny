package com.yelstream.topp.util.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.random.RandomGenerator;

/**
 * Generates random content.
 * This is aimed at ad hoc testing and when files of specific sizes are necessary.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-06
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName="Builder",toBuilder=true)
public class RandomContentGenerator {
    @lombok.Builder.Default
    private final int bufferSize=1024;

    @lombok.Builder.Default
    private final RandomGenerator random=new SecureRandom();

    @Getter(lazy=true)
    private final byte[] buffer=new byte[bufferSize];

    /**
     * Creates random content.
     * @param filePath Destination file.
     * @param size Size of file.
     * @return Destination file.
     * @throws IOException Thrown in case of I/O error.
     */
    public Path createContent(Path filePath,
                              long size) throws IOException {
        if (Files.exists(filePath)) {
            throw new IOException(String.format("Failure to create content; file '%s' does already exist!",filePath));
        }
        Files.createFile(filePath);
        try (OutputStream out=Files.newOutputStream(filePath)) {
            addContent(out,size);
            out.flush();
        }
        return filePath;
    }

    /**
     * Adds random content.
     * @param out Destination stream.
     * @param size Size of file.
     * @throws IOException Thrown in case of I/O error.
     */
    public void addContent(OutputStream out,
                           long size) throws IOException {
        byte[] b=getBuffer();
        long actualSize=0;
        while (actualSize<size) {
            int l=(int)Math.min(b.length,size-actualSize);
            random.nextBytes(b);
            out.write(b,0,l);
            actualSize+=l;
        }
    }
}
