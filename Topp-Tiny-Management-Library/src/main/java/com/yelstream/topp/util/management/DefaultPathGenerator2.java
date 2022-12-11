package com.yelstream.topp.util.management;

import com.yelstream.topp.util.format.NamedFormatters;
import com.yelstream.topp.lang.StringBuilders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Default implementation of a path generator.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-04
 */
@Builder(builderClassName="Builder",toBuilder=true)
@AllArgsConstructor
public final class DefaultPathGenerator2 implements PathGenerator {
    @lombok.Builder.Default
    private final Path directory=Paths.get(".");

    private final String fileNameFormat;

    @lombok.Builder.Default
    private final DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss-SSS");

    /**
     * File name prefix.
     */
    private final String fileNamePrefix;

    /**
     * File name suffix.
     */
    private final String fileNameSuffix;

    /**
     * File name extension.
     */
    private final String fileNameExtension;

    /**
     * Source of timestamp formatted into file name.
     */
    private final Supplier<TemporalAccessor> timeSupplier;

    private record InstanceData(String fileNameFormat) {
    }

    @Getter(lazy=true)
    private final InstanceData instanceData=createInstanceData();

    @Override
    @SuppressWarnings("java:S1117")  //"Local variables should not shadow class fields"
    public Path generate(String partName) {
        InstanceData instanceData=getInstanceData();
        String pathName=createPathName(instanceData.fileNameFormat,timeSupplier,dateFormatter,partName);
        return directory.resolve(pathName);
    }

    /*
    @Override
    @SuppressWarnings("java:S1117")  //"Local variables should not shadow class fields"
    public Path generate(List<Part> parts) {
        InstanceData instanceData=getInstanceData();
        String pathName=createPathName(instanceData.fileNameFormat,timeSupplier,dateFormatter,partName);
        return directory.resolve(pathName);
    }
    */

    private static String createPathName(String fileNameFormat,
                                         Supplier<TemporalAccessor> timeSupplier,
                                         DateTimeFormatter dateFormatter,
                                         String partName) {
        Map<String,Object> arguments=new HashMap<>();
        arguments.put("partName",partName);
        String formattedTime=null;
        if (timeSupplier!=null) {
            TemporalAccessor time=timeSupplier.get();
            formattedTime=dateFormatter.format(time);
            arguments.put("formattedTime",formattedTime);
        }
        return NamedFormatters.format(fileNameFormat,arguments);
    }

    /**
     * Build a path generator.
     * @return Path generator.
     */
    //@SuppressWarnings("java:S3776")  //"Cognitive Complexity of methods should not be too high"
    private InstanceData createInstanceData() {
        String fileNameFormat;

        if (this.fileNameFormat!=null) {
            if (fileNamePrefix!=null || fileNameSuffix!=null || fileNameExtension!=null) {
                throw new IllegalArgumentException("Failure to build; cannot set any one of file name prefix, suffix or extension while also setting the full format!");
            }
            fileNameFormat=this.fileNameFormat;
        } else {
            StringBuilder metaFileNameFormatBuffer=new StringBuilder();
            Map<String,Object> arguments=new HashMap<>();

            if (fileNamePrefix!=null) {
                metaFileNameFormatBuffer.append("%fileNamePrefix$s");
                arguments.put("fileNamePrefix",fileNamePrefix);
            }

            if (timeSupplier!=null) {
                StringBuilders.appendTokenIfBuilderIsNonEmpty(metaFileNameFormatBuffer,"-");
                metaFileNameFormatBuffer.append("%%formattedTime$s");
            }

            StringBuilders.appendTokenIfBuilderIsNonEmpty(metaFileNameFormatBuffer,"-");
            metaFileNameFormatBuffer.append("%%partName$s");

            if (fileNameSuffix!=null) {
                StringBuilders.appendTokenIfBuilderIsNonEmpty(metaFileNameFormatBuffer,"-");
                metaFileNameFormatBuffer.append("%fileNameSuffix$s");
                arguments.put("fileNameSuffix",fileNameSuffix);
            }

            if (fileNameExtension!=null) {
                StringBuilders.appendDelimiterIfNotOnToken(metaFileNameFormatBuffer, ".",fileNameExtension);
                metaFileNameFormatBuffer.append("%fileNameExtension$s");
                arguments.put("fileNameExtension",fileNameExtension);
            }

            String metaFileNameFormat=metaFileNameFormatBuffer.toString();
            fileNameFormat=NamedFormatters.format(metaFileNameFormat,arguments);
        }

        return new InstanceData(fileNameFormat);
    }

    /**
     * .
     */
    public static class Builder {
        @SuppressWarnings({"java:S1450","FieldCanBeLocal","unused"})
        private Supplier<TemporalAccessor> timeSupplier;

        /**
         * .
         * @param timeSupplier .
         * @return .
         */
        public Builder timeSupplier(Supplier<TemporalAccessor> timeSupplier) {
            if (timeSupplier!=null) {
                throw new IllegalStateException("Failure to set time; cannot set time more than once!");
            }
            this.timeSupplier=timeSupplier;
            return this;
        }

        /**
         * .
         * @param time .
         * @return .
         */
        public Builder time(TemporalAccessor time) {
            return timeSupplier(()->time);
        }
    }

    /**
     * .
     * @param args .
     */
    public static void main(String[] args) {
        DefaultPathGenerator2 generator=DefaultPathGenerator2.builder().fileNamePrefix("prefix").fileNameSuffix("suffix").fileNameExtension(".bin").build();
        System.out.println(generator.generate("aaa"));
        System.out.println(generator.generate("bbb"));
        System.out.println(generator.generate("ccc"));
    }
}
