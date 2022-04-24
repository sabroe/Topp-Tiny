package com.yelstream.topp.util.net;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Utilities addressing instances of {@link URI}.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-04-23
 */
@UtilityClass
public class URIs {
    /**
     * Creates a URI from a URL.
     * No checked exception is thrown.
     * @param url URL.
     * @return URI.
     */
    public URI createURI(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException(String.format("Failure to create URI; existing URL is %s!",url),ex);
        }
    }

    /**
     * Creates a URI with no fragment, just the scheme and the scheme-specific part.
     * No checked exception is thrown.
     * @param uri URI.
     *            This may have an existing fragment.
     * @return URI with no fragment.
     */
    public URI createURIWithoutFragment(URI uri) {
        try {
            if (uri==null) {
                return null;
            } else {
                if ((uri.getScheme()==null || uri.getScheme().isEmpty()) && (uri.getSchemeSpecificPart()==null || uri.getSchemeSpecificPart().isEmpty())) {
                    return null;
                } else {
                    return new URI(uri.getScheme(), uri.getSchemeSpecificPart(), null);
                }
            }
        } catch (URISyntaxException ex) {
            throw new IllegalStateException(String.format("Failure to create URI; existing URI is %s!",uri),ex);
        }
    }

    /**
     * Creates a URI with a specific fragment (added or replaced).
     * No checked exception is thrown.
     * @param uri URI.
     *            This may have an existing fragment.
     * @param fragment Fragment.
     * @return URI with no fragment.
     */
    public URI createURIWithNewFragment(URI uri,
                                        String fragment) {
        try {
            if (uri==null) {
                return new URI(null,null,fragment);
            } else {
                return new URI(uri.getScheme(),uri.getSchemeSpecificPart(),fragment);
            }
        } catch (URISyntaxException ex) {
            throw new IllegalStateException(String.format("Failure to create URI; existing URI is %s, fragment is %s!",uri,fragment),ex);
        }
    }

    /**
     * Creates a URI with a specific fragment added.
     * No checked exception is thrown.
     * @param uri URI.
     *            This must not have an existing fragment.
     * @param fragment Fragment.
     * @return URI with no fragment.
     */
    public URI createURIWithFragment(URI uri,
                                     String fragment) {
        if (uri!=null && uri.getFragment()!=null) {
            throw new IllegalStateException(String.format("Failure to create URI; existing URI %s has a fragment!",uri));
        }
        if (fragment==null) {
            return createURIWithoutFragment(uri);
        } else {
            return createURIWithNewFragment(uri,fragment);
        }
    }

    /**
     * Indicates, if a URI has a path.
     * @param uri URI.
     * @return Indicates, if the URI has a path.
     */
    public boolean hasPath(URI uri) {
        String path=uri.getPath();
        return path!=null && !path.isEmpty();
    }

    /**
     * Indicates, if a URI has a fragment only.
     * This implies that the URI has no scheme and no scheme-specific part.
     * @param uri URI.
     * @return Indicates, if the URI has a fragment only.
     */
    public boolean hasFragmentOnly(URI uri) {
        String scheme=uri.getScheme();
        String ssp=uri.getSchemeSpecificPart();
        return (scheme==null || scheme.isEmpty()) && (ssp==null || ssp.isEmpty());
    }

    /**
     * Gets the schema of a URI.
     * @param uri URI.
     * @return Scheme.
     */
    public String getScheme(URI uri) {
        String res=null;
        String scheme=uri.getScheme();
        if (scheme!=null && !scheme.isEmpty()) {
            res=scheme;
        }
        return res;
    }
}
