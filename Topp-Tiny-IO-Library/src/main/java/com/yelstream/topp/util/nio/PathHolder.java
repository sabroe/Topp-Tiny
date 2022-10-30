package com.yelstream.topp.util.nio;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;

@AllArgsConstructor
@Getter
public final class PathHolder implements Closeable {
    private final Path path;

    @Override
    public void close() throws IOException {
        if (path!=null) {
            PathUtility.delete(path);
        }
    }
}
