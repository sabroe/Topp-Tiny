package com.yelstream.topp.util.management;

import java.nio.file.Path;

/**
 * Generates a {@link Path} from a name.
 * This name may become a part of the path.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-10-30
 */
@FunctionalInterface
public interface PathGenerator {
    /**
     * Generates a path.
     * @param partName Partial name of path.
     * @return Path.
     */
    Path generate(String partName);
}
