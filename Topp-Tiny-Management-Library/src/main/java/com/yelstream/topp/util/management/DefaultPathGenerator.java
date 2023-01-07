package com.yelstream.topp.util.management;

import com.yelstream.topp.lang.StringBuilders;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.function.Supplier;

/**
 * Default implementation of a path generator.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-04
 */
@Builder(builderClassName="Builder")
@AllArgsConstructor
public final class DefaultPathGenerator implements PathGenerator {
    private final Path directory;

    private final String fileNameFormat;

    private final Supplier<TemporalAccessor> timeSupplier;

    private final DateTimeFormatter dateFormatter;

    @Override
    public Path generate(String partName) {
        String formattedTime=null;
        if (timeSupplier!=null) {
            TemporalAccessor time=timeSupplier.get();
            formattedTime=dateFormatter.format(time);
        }
        String pathName=String.format(fileNameFormat,partName,formattedTime);  //Yes, part name first, formatted time second!
        return directory.resolve(pathName);
    }

    /**
     * Builder of path generators.
     */
    @SuppressWarnings("FieldMayBeFinal")
    public static class Builder {
        /**
         * Directory pointed to by generated file references.
         */
        private Path directory=Paths.get("'");

        /**
         * Format of file names.
         */
        private String fileNameFormat;

        private Supplier<TemporalAccessor> timeSupplier;

        private DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss-SSS");

        /**
         * File name prefix.
         */
        private String fileNamePrefix;

        /**
         * File name suffix.
         */
        private String fileNameSuffix;

        /**
         * File name extension.
         */
        private String fileNameExtension;

        /**
         * Timestamp formatted into file name.
         */
        private TemporalAccessor time;

        /**
         * Sets the file name prefix.
         * @param fileNamePrefix File name prefix.
         * @return Builder.
         */
        public Builder fileNamePrefix(String fileNamePrefix) {
            this.fileNamePrefix=fileNamePrefix;
            return this;
        }

        /**
         * Sets the file name suffix.
         * @param fileNameSuffix File name suffix.
         * @return Builder.
         */
        public Builder fileNameSuffix(String fileNameSuffix) {
            this.fileNameSuffix=fileNameSuffix;
            return this;
        }

        /**
         * Sets the file name extension.
         * @param fileNameExtension File name extension.
         * @return Builder.
         */
        public Builder fileNameExtension(String fileNameExtension) {
            this.fileNameExtension=fileNameExtension;
            return this;
        }

        /**
         * Sets the time.
         * @param time Time.
         * @return Builder.
         */
        public Builder time(TemporalAccessor time) {
            this.time=time;
            return this;
        }

        /**
         * Sets the file name format.
         * @param fileNameFormat File name format.
         * @return Builder.
         */
        public Builder fileNameFormat(String fileNameFormat) {
            this.fileNameFormat=fileNameFormat;
            return this;
        }

        /**
         * Set the format of file names.
         * @param fileNamePrefix File name prefix.
         * @param fileNameSuffix File name suffix.
         * @param fileNameExtension File name extension.
         * @return Builder.
         */
        public Builder fileNameFormat(String fileNamePrefix,
                                      String fileNameSuffix,
                                      String fileNameExtension) {
            this.fileNamePrefix=fileNamePrefix;
            this.fileNameSuffix=fileNameSuffix;
            this.fileNameExtension=fileNameExtension;
            return this;
        }

        /**
         * Build a path generator.
         * @return Path generator.
         */
        @SuppressWarnings("java:S3776")  //"Cognitive Complexity of methods should not be too high"
        public DefaultPathGenerator build() {
            StringBuilder metaFileNameFormatBuffer=new StringBuilder();

            if (fileNameFormat!=null) {
                if (fileNamePrefix!=null || fileNameSuffix!=null || fileNameExtension!=null) {
                    throw new IllegalArgumentException("Failure to build; cannot set any one of file name prefix, suffix or extension while also setting the full format!");
                }
            } else {
                if (fileNamePrefix!=null) {
                    metaFileNameFormatBuffer.append("%1$s");
                    metaFileNameFormatBuffer.append("-");
                }

                if (time==null) {
                    if (timeSupplier==null) {
                        metaFileNameFormatBuffer.append("%%1$s");  //part-name
                    } else {
                        metaFileNameFormatBuffer.append("%%1$s-%%2$s");  //part-name first, time second
                    }
                } else {
                    if (timeSupplier==null) {
                        metaFileNameFormatBuffer.append("%%2$s-%%1$s");  //fixed time first, part-name second
                    } else {
                        throw new IllegalArgumentException("Failure to build; cannot set both instant and instant-supplier!");
                    }
                    timeSupplier=()->time;
                }

                if (fileNameSuffix!=null) {
                    StringBuilders.appendTokenIfBuilderIsNonEmpty(metaFileNameFormatBuffer,"-");
                    metaFileNameFormatBuffer.append("%2$s");
                }

                if (fileNameExtension!=null) {
                    StringBuilders.appendDelimiterIfNotOnToken(metaFileNameFormatBuffer,".",fileNameExtension);
                    metaFileNameFormatBuffer.append("%3$s");
                }

                String metaFileNameFormat=metaFileNameFormatBuffer.toString();
                fileNameFormat=String.format(metaFileNameFormat,fileNamePrefix,fileNameSuffix,fileNameExtension);
            }

            return new DefaultPathGenerator(directory,fileNameFormat,timeSupplier,dateFormatter);
        }
    }
}
