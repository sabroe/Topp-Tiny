package com.yelstream.topp.util.io;

import com.yelstream.topp.util.nio.PathUtility;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Utilities addressing instances of {@link File}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-10-30
 */
@UtilityClass
@SuppressWarnings("unused")
public class FileUtility {
    /**
     * Creates a temporary file.
     * @param directory Directory to contain file.
     * @param prefix Directory name prefix.
     * @param suffix Directory name suffix.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static File createTempFile(File directory,
                                      String prefix,
                                      String suffix) throws IOException {
        Path path=PathUtility.createTempFile(directory.toPath(),prefix,suffix);
        return path.toFile();
    }

    /**
     * Creates a temporary file.
     * @param directory Directory to contain file.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static File createTempFile(File directory) throws IOException {
        return createTempFile(directory,null,null);
    }

    /**
     * Creates a temporary file.
     * @param prefix Directory name prefix.
     * @param suffix Directory name suffix.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static File createTempFile(String prefix,
                                      String suffix) throws IOException {
        Path path=PathUtility.createTempFile(prefix,suffix);
        return path.toFile();
    }

    /**
     * Creates a temporary file.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static File createTempFile() throws IOException {
        return createTempFile(null,null);
    }

    /**
     * Creates a temporary directory.
     * @param prefix Directory name prefix.
     * @return Reference to directory.
     * @throws IOException Thrown in case of I/O error.
     */
    public static File createTempDirectory(String prefix) throws IOException {
        Path path=PathUtility.createTempDirectory(prefix);
        return path.toFile();
    }

    /**
     * Creates a temporary directory.
     * @return Reference to directory.
     * @throws IOException Thrown in case of I/O error.
     */
    public static File createTempDirectory() throws IOException {
        return createTempDirectory(null);
    }

    /**
     * Deletes a file.
     * This differs from {@link File#delete()} by throwing an exception in case of failure.
     * @param file Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static void delete(File file) throws IOException {
        if (file!=null) {
            Path path= file.toPath();
            PathUtility.delete(path);
        }
    }
}
