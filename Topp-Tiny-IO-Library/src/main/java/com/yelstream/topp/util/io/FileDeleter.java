package com.yelstream.topp.util.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * Deletes a file when used in a try-with-resources.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-10-30
 */
@AllArgsConstructor
@Getter
public final class FileDeleter implements Closeable {
    private final File file;

    @Override
    public void close() throws IOException {
        if (file!=null) {
            FileUtility.delete(file);
        }
    }
}
