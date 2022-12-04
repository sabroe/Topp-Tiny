/**
 * Topp Tiny Management is an open-source Java library which implements management related utilities.
 */
module com.yelstream.topp.tiny.management {
    requires static lombok;
    requires jdk.management;
    requires com.yelstream.topp.tiny.format;
    exports com.yelstream.topp.util.management;
}
