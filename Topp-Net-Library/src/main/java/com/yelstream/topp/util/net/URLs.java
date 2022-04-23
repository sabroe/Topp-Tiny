package com.yelstream.topp.util.net;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Utilities addressing instances of {@link URL}.
 */
@UtilityClass
public class URLs {
    /**
     * Creates a URL from a URI.
     * In case of error then no checked exception is thrown.
     * @param uri URI.
     * @return URL.
     */
    public URL createURL(URI uri) {
        try {
            return uri.toURL();
        } catch (MalformedURLException ex) {
            throw new IllegalStateException(String.format("Failure to create URL from URI; URI is %s!",uri),ex);
        }
    }

    /**
     * Creates a URL from a file handle.
     * In case of error then no checked exception is thrown.
     * @param file File handle.
     * @return URL.
     */
    public URL createURL(File file) {
        return createURL(file.toURI());
    }

    /**
     * Opens a connection to a URL and returns a stream for reading from that connection.
     * @param url URL.
     * @param useCaches Indicates, if caches are to be used.
     * @return Stream for reading.
     * @throws IOException Thrown in case of I/O error.
     */
    public InputStream openStream(URL url,
                                  boolean useCaches) throws IOException {
        if (useCaches) {
            return url.openStream();
        } else {
            return openStreamWithNoCache(url);
        }
    }

    /**
     * Opens a connection to a URL and returns a stream for reading from that connection.
     * No caches are used.
     * @param url URL.
     * @return Stream for reading.
     * @throws IOException Thrown in case of I/O error.
     */
    public InputStream openStreamWithNoCache(URL url) throws IOException {
        URLConnection connection=url.openConnection();
        connection.setUseCaches(false);
        return connection.getInputStream();
    }
}
