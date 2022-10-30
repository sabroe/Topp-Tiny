package com.yelstream.topp.util.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Getter
public final class FileHolder implements Closeable {
    private final File file;

    @Override
    public void close() throws IOException {
        if (file!=null) {
            FileUtility.delete(file);
        }
    }
}
