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

    public void createContent(Path path,
                              long size) throws IOException {
        Files.createFile(path);
        try (OutputStream out=Files.newOutputStream(path)) {
            createContent(out,size);
            out.flush();
        }
    }

    public void createContent(OutputStream out,
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
