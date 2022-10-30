package com.yelstream.topp.util.management;

import java.nio.file.Path;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
@FunctionalInterface
public interface PathGenerator {
    Path generate(String partName);

    static PathGenerator of(Path directory,
                            String fileNamePrefix,
                            String fileNameSuffix,
                            String filenameExtension) {
        return of(directory,part->String.format("%s-%s-%s.%s",fileNamePrefix,part,fileNameSuffix,filenameExtension));
    }

    static PathGenerator of(Path directory,
                            String fileNamePrefix,
                            String filenameExtension) {
        return of(directory,part->String.format("%s-%s.%s",fileNamePrefix,part,filenameExtension));
    }

    static PathGenerator of(Path directory,
                            UnaryOperator<String> partNameToFullFileName) {
        return part->{
            String fileName=partNameToFullFileName.apply(part);
            return directory.resolve(fileName);
        };
    }
}
