package com.yelstream.topp.util.management;

import java.nio.file.Path;
import java.util.function.UnaryOperator;

/**
 * Generates a {@link Path} from a name.
 * This name may become a part of the path.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-10-30
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface PathGenerator {
    Path generate(String partName);

    /**
     * Creates a path generator.
     * @param directory Directory of files to generate paths for.
     * @param fileNamePrefix Prefix of file name.
     * @param fileNameSuffix Suffix of file name.
     * @param filenameExtension Extension of file name.
     * @return Path generator.
     */
    static PathGenerator of(Path directory,
                            String fileNamePrefix,
                            String fileNameSuffix,
                            String filenameExtension) {
        return of(directory,part->String.format("%s-%s-%s.%s",fileNamePrefix,part,fileNameSuffix,filenameExtension));
    }

    /**
     * Creates a path generator.
     * @param directory Directory of files to generate paths for.
     * @param fileNamePrefix Prefix of file name.
     * @param filenameExtension Extension of file name.
     * @return Path generator.
     */
    static PathGenerator of(Path directory,
                            String fileNamePrefix,
                            String filenameExtension) {
        return of(directory,part->String.format("%s-%s.%s",fileNamePrefix,part,filenameExtension));
    }

    /**
     * Creates a path generator.
     * @param directory Directory of files to generate paths for.
     * @param partNameToFullFileName Maps a part name to a full, complete file name.
     * @return Path generator.
     */
    static PathGenerator of(Path directory,
                            UnaryOperator<String> partNameToFullFileName) {
        return part->{
            String fileName=partNameToFullFileName.apply(part);
            return directory.resolve(fileName);
        };
    }
}
