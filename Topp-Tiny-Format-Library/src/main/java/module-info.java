/**
 * Topp Tiny Format is an open-source Java library which implements formatting utilities.
 */
module com.yelstream.topp.tiny.format {
    requires static lombok;
    requires com.yelstream.topp.tiny.collection;
    exports com.yelstream.topp.lang;
    exports com.yelstream.topp.util.format;
    exports com.yelstream.topp.util.random;
    exports com.yelstream.topp.util.random.data;
    exports com.yelstream.topp.util.regex;
    exports com.yelstream.topp.util.uuid;
}
