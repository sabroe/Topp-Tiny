package com.yelstream.topp.util.nio;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Deletes a path when used in a try-with-resources.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-10-30
 */
@AllArgsConstructor
@Getter
public final class PathDeleter implements Closeable {
    private final Path path;

    @Override
    public void close() throws IOException {
        if (path!=null) {
            PathUtility.delete(path);
        }
    }
}
