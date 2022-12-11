package com.yelstream.topp.util.management;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
    /*
     * Generates a path.
     * @param parts Partial names of path.
     * @return Path.
     */
    /*
    default Path generate(String... parts) {
        List<Part> partList=IntStream.range(0,parts.length).mapToObj(i->new Part(Integer.toString(i),parts[i])).toList();
        return generate(partList);
    }
    */

    /*
    record Part(String name, String value) {
    }
    */

    /*
     * Generates a path.
     * @param parts Partial names of path.
     * @return Path.
     */
    /*
    default Path generate(Part... parts) {
        List<Part> partList=Arrays.stream(parts).toList();
        return generate(partList);
    }
    */

    /*
    Path generate(List<Part> parts);
    */

    /**
     * .
     * @param partName .
     * @return .
     */
    Path generate(String partName);
}
