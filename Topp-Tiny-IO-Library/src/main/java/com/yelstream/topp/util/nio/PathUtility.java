package com.yelstream.topp.util.nio;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Utilities addressing instances of {@link Path}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-10-30
 */
@UtilityClass
@SuppressWarnings("unused")
public class PathUtility {
    /**
     * Creates default POSIX file permissions.
     * @return File permissions.
     */
    private static Set<PosixFilePermission> createPosixFilePermissions() {
        return PosixFilePermissions.fromString("rwx------");
    }

    /**
     * Creates default file attributes
     * @return File attributes.
     */
    private static FileAttribute<Set<PosixFilePermission>> createFileAttributes() {
        Set<PosixFilePermission> permissions=createPosixFilePermissions();
        return PosixFilePermissions.asFileAttribute(permissions);
    }

    /**
     * Creates a temporary file.
     * @param directory Directory to contain file.
     * @param prefix Directory name prefix.
     * @param suffix Directory name suffix.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static Path createTempFile(Path directory,
                                      String prefix,
                                      String suffix) throws IOException {
        FileAttribute<?> attributes=createFileAttributes();
        return Files.createTempFile(directory,prefix,suffix,attributes);
    }

    /**
     * Creates a temporary file.
     * @param prefix Directory name prefix.
     * @param suffix Directory name suffix.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     * @see <a href="https://sonarsource.atlassian.net/browse/RSPEC-5445">RSPEC-5445</a>
     * @see <a href="https://sonarsource.atlassian.net/browse/RSPEC-6120">RSPEC-6120</a>
     */
    public static Path createTempFile(String prefix,
                                      String suffix) throws IOException {
        FileAttribute<?> attributes=createFileAttributes();
        return Files.createTempFile(prefix,suffix,attributes);
    }

    /**
     * Creates a temporary file.
     * @param directory Directory to contain file.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static Path createTempFile(Path directory) throws IOException {
        return createTempFile(directory,null,null);
    }

    /**
     * Creates a temporary file.
     * @return Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static Path createTempFile() throws IOException {
        return createTempFile(null,null);
    }

    /**
     * Creates a temporary file.
     * @param prefix Directory name prefix.
     * @return Reference to directory.
     * @throws IOException Thrown in case of I/O error.
     * @see <a href="https://sonarsource.atlassian.net/browse/RSPEC-5443">RSPEC-5443</a>
     * @see <a href="https://sonarsource.atlassian.net/browse/RSPEC-6142">RSPEC-6142</a>
     */
    public static Path createTempDirectory(String prefix) throws IOException {
        FileAttribute<?> attributes=createFileAttributes();
        return Files.createTempDirectory(prefix,attributes);
    }

    /**
     * Creates a temporary file.
     * @return Reference to directory.
     * @throws IOException Thrown in case of I/O error.
     */
    public static Path createTempDirectory() throws IOException {
        return createTempDirectory(null);
    }

    /**
     * Deletes a file.
     * @param path Reference to file.
     * @throws IOException Thrown in case of I/O error.
     */
    public static void delete(Path path) throws IOException {
        if (path!=null) {
            Files.delete(path);
        }
    }
}
