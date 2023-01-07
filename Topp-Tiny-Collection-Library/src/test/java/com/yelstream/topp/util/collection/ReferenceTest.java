package com.yelstream.topp.util.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test suite for {@code com.yelstream.topp.collection.Reference}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-17
 */
class ReferenceTest {
    /**
     * Tests object {@code com.yelstream.topp.collection.Reference}.
     */
    @Test
    void reference() {
        {
            Reference<String> reference = new Reference<>();
            Assertions.assertNull(reference.getElement());
        }
        {
            Reference<String> reference = new Reference<>("xxx");
            Assertions.assertEquals("xxx",reference.getElement());
        }
    }
}
