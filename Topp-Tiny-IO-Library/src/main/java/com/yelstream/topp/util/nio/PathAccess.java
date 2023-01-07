package com.yelstream.topp.util.nio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

/**
 * Generates random content.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-18
 */
@Getter
@AllArgsConstructor
@Builder(builderClassName="Builder",toBuilder=true)
public class PathAccess {

    private final Path path;

    @lombok.Builder.Default
    private final FileAttribute<?>[] directoriesAttrs=new FileAttribute<?>[0];

    @lombok.Builder.Default
    private final FileAttribute<?>[] fileAttrs=new FileAttribute<?>[0];

    @lombok.Builder.Default
    private final OpenOption[] openOptions=null;

    /**
     * Creates all directories to the path.
     * @return Path.
     * @throws IOException Thrown in case of I/O error.
     */
    public Path createDirectories() throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path,directoriesAttrs);
        }
        return path;
    }

    /**
     * Creates all directories and the file given by the path.
     * @return Path.
     * @throws IOException Thrown in case of I/O error.
     */
    public Path createFile() throws IOException {
        Path parent=path.getParent();
        if (parent!=null) {
            if (!Files.exists(parent)) {
                Files.createDirectories(parent,directoriesAttrs);
            }
        }
        return Files.createFile(path,fileAttrs);
    }

    /**
     * Creates an output stream to write to the file given by the path.
     * @return Stream.
     * @throws IOException Thrown in case of I/O error.
     */
    public OutputStream newOutputStream() throws IOException {
        return Files.newOutputStream(path,openOptions);
    }

    /**
     * .
     * @param args .
     * @throws IOException .
     */
    public static void main(String[] args) throws IOException {
        PathAccess access=PathAccess.builder().path(Paths.get("build/temp/aaa/xxx.txt")).build();
        access.createFile();
    }
}
