package com.yelstream.topp.util.collection;

import com.yelstream.topp.util.function.ex.FunctionWithException;
import com.yelstream.topp.util.function.ex.SupplierWithException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test suite for {@code com.yelstream.topp.collection.Maps}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-17
 */
public class MapsTest {
    /**
     * Tests utility method {@link Maps#registerVisit(Map, Object)}.
     */
    @Test
    void registerVisit() {
        Map<String,Boolean> map=new HashMap<>();
        Assertions.assertThrows(IllegalArgumentException.class,()->Maps.registerVisit(map,null));
        Assertions.assertFalse(Maps.registerVisit(map,"xxx"));
        Assertions.assertTrue(Maps.registerVisit(map,"xxx"));
        Assertions.assertEquals(Map.of("xxx",true),map);
    }

    /**
     * Tests utility method {@link Maps#putFromScratch(Map, Object, Object)}.
     */
    @Test
    void putFromScratch() {
        Map<String,Boolean> map=new HashMap<>();
        Assertions.assertThrows(IllegalArgumentException.class,()->Maps.putFromScratch(map,null,null));
        Assertions.assertDoesNotThrow(()->Maps.putFromScratch(map,"xxx",true));
        Assertions.assertThrows(IllegalStateException.class,()->Maps.putFromScratch(map,"xxx",true));
    }

    /**
     * Tests utility method {@link Maps#updateIfAbsent(Map, Object, Object)}.
     */
    @Test
    void updateIfAbsent() {
        Map<String,Boolean> map=new HashMap<>();
        Assertions.assertThrows(IllegalArgumentException.class,()->Maps.updateIfAbsent(map,null,null));

        Assertions.assertNull(Maps.updateIfAbsent(map, "xxx", null));
        Assertions.assertTrue(map.containsKey("xxx"));

        map.put("xxx",Boolean.FALSE);
        Assertions.assertEquals(Boolean.FALSE,Maps.updateIfAbsent(map,"xxx",true));
        Assertions.assertEquals(Boolean.FALSE,Maps.updateIfAbsent(map,"xxx",false));
        Assertions.assertEquals(Map.of("xxx",false),map);
    }

    /**
     * Tests utility method {@link Maps#computeIfPresent(Map, Object, SupplierWithException)}.
     */
    @Test
    void computeIfPresentWithSupplier() {
        Map<String,Boolean> map=new HashMap<>();
        Assertions.assertThrows(IllegalArgumentException.class,()->Maps.computeIfPresent(map,null,()->null));

        map.put("xxx",null);
        Assertions.assertTrue(map.containsKey("xxx"));
        Assertions.assertEquals(Boolean.FALSE,Maps.computeIfPresent(map, "xxx",()->Boolean.FALSE));
        Assertions.assertEquals(Map.of("xxx",Boolean.FALSE),map);

        Assertions.assertNull(Maps.computeIfPresent(map, "yyy",()->Boolean.FALSE));

        Assertions.assertNull(Maps.computeIfPresent(map, "xxx",()->null));
        Assertions.assertFalse(map.containsKey("xxx"));
    }

    /**
     * Tests utility method {@link Maps#computeIfPresent(Map, Object, FunctionWithException)}.
     */
    @Test
    void computeIfPresentWithFunction() {
        Map<String,Boolean> map=new HashMap<>();
        Assertions.assertThrows(IllegalArgumentException.class,()->Maps.computeIfPresent(map,null,(k)->null));

        map.put("xxx",null);
        Assertions.assertTrue(map.containsKey("xxx"));
        Assertions.assertEquals(Boolean.FALSE,Maps.computeIfPresent(map, "xxx",(k)->Boolean.FALSE));
        Assertions.assertEquals(Map.of("xxx",Boolean.FALSE),map);

        Assertions.assertNull(Maps.computeIfPresent(map, "yyy",(k)->Boolean.FALSE));

        Assertions.assertNull(Maps.computeIfPresent(map, "xxx",(k)->null));
        Assertions.assertFalse(map.containsKey("xxx"));
    }
}
