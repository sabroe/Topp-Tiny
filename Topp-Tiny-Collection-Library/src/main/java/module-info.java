/**
 * Topp Tiny Collection is an open-source Java library which implements collection related utilities.
 */
module com.yelstream.topp.tiny.collection {
    requires static lombok;
    requires transitive com.yelstream.topp.tiny.function;
    exports com.yelstream.topp.util.collection;
    exports com.yelstream.topp.util.feature;
}
