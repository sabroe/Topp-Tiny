package com.yelstream.topp.util.collection;

import com.yelstream.topp.util.function.ex.BiFunctionWithException;
import com.yelstream.topp.util.function.ex.FunctionWithException;
import com.yelstream.topp.util.function.ex.SupplierWithException;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * Utilities addressing instances of {@link Map}.
 */
@UtilityClass
public class Maps {
    /**
     * Marks a key as having been "visited".
     * This is used in connection with graph traversals.
     * @param map Map
     * @param key Key.
     * @param <K> Type of key.
     * @return Indicates, if the key has already been marked as "visited".
     */
    public <K> boolean registerVisit(Map<K,Boolean> map,
                                     K key) {
        if (key==null) {
            throw new IllegalArgumentException("Failure to register visit in map; key is not set!");
        }
        Boolean v=map.get(key);
        boolean visited=v==Boolean.TRUE;
        if (!visited) {
            map.put(key,Boolean.TRUE);
        }
        return visited;
    }

    /**
     * Ensures that a key is mapped to a value while requiring that the key is not already mapped.
     * @param map Map.
     * @param key Key.
     * @param value Value.
     * @param <K> Type of key.
     * @param <V> Type of value.
     */
    public <K,V> void putFromScratch(Map<K,V> map,
                                     K key,
                                     V value) {
        if (key==null) {
            throw new IllegalArgumentException("Failure to put entry into map from scratch; key is not set!");
        }
        if (map.containsKey(key)) {
            throw new IllegalStateException(String.format("Failure to put entry into map from scratch; map already contains key %s!",key));
        }
        map.put(key,value);
    }

    /**
     * Ensures that a key is mapped to a value.
     * Note that the semantics of this differs from {@link Map#putIfAbsent(Object,Object)} in that
     * the result of updating the map is not the previous value, but the new value given.
     * @param map Map.
     * @param key Key.
     * @param value value;
     * @return The current value (existing or computed).
     * @param <K> Type of key.
     * @param <V> Type of value.
     */
    public <K,V> V updateIfAbsent(Map<K,V> map,
                                  K key,
                                  V value) {
        return computeIfAbsent(map,key,()->value);
    }

    /**
     * Ensures that a key is mapped to a value which is created if missing.
     * @param map Map.
     * @param key Key.
     * @param valueSupplier Supplier of a new value.
     * @return The current value (existing or computed).
     * @throws E Thrown in case of error.
     * @param <K> Type of key.
     * @param <V> Type of value.
     * @param <E> Type of exception thrown.
     */
    public <K,V,E extends Throwable> V computeIfAbsent(Map<K,V> map,
                                                       K key,
                                                       SupplierWithException<V,E> valueSupplier) throws E {
        return computeIfAbsent(map,key,(k)->valueSupplier.get());
    }

    /**
     * Ensures that a key is mapped to a value which is created if missing.
     * @param map Map.
     * @param key Key.
     * @param mappingFunction Supplier of a new value.
     * @return The current value (existing or computed).
     * @throws E Thrown in case of error.
     * @param <K> Type of key.
     * @param <V> Type of value.
     * @param <E> Type of exception thrown.
     */
    public <K,V,E extends Throwable> V computeIfAbsent(Map<K,V> map,
                                                       K key,
                                                       FunctionWithException<? super K,? extends V,? extends E> mappingFunction) throws E {
        V res;
        if (key==null) {
            throw new IllegalArgumentException("Failure to ensure entry is present in map; key is not set!");
        }
        res=map.get(key);
        if (!map.containsKey(key)) {
            V value=mappingFunction.apply(key);
            if (value!=null) {
                map.put(key, value);
                res = value;
            }
        }
        return res;
    }

    /**
     * Ensures that a key is mapped to a value which is created if missing.
     * @param map Map.
     * @param key Key.
     * @param valueSupplier Supplier of a new value.
     * @return The current value (existing or computed).
     * @throws E Thrown in case of error.
     * @param <K> Type of key.
     * @param <V> Type of value.
     * @param <E> Type of exception thrown.
     */
    public <K,V,E extends Throwable> V computeIfPresent(Map<K,V> map,
                                                        K key,
                                                        SupplierWithException<V,E> valueSupplier) throws E {
        return computeIfPresent(map,key,(k,v)->valueSupplier.get());
    }

    /**
     * Ensures that a key is mapped to a value which is created if missing.
     * @param map Map.
     * @param key Key.
     * @param mappingFunction Supplier of a new value.
     * @return The current value (existing or computed).
     * @throws E Thrown in case of error.
     * @param <K> Type of key.
     * @param <V> Type of value.
     * @param <E> Type of exception thrown.
     */
    public <K,V,E extends Throwable> V computeIfPresent(Map<K,V> map,
                                                        K key,
                                                        FunctionWithException<? super K,? extends V,? extends E> mappingFunction) throws E {
        return computeIfPresent(map,key,(k,v)->mappingFunction.apply(k));
    }

    /**
     * Ensures that a key is mapped to a value which is created if missing.
     * @param map Map.
     * @param key Key.
     * @param mappingFunction Supplier of a new value.
     * @return The current value (existing or computed).
     * @throws E Thrown in case of error.
     * @param <K> Type of key.
     * @param <V> Type of value.
     * @param <E> Type of exception thrown.
     */
    @SuppressWarnings("java:S3824")
    public <K,V,E extends Throwable> V computeIfPresent(Map<K,V> map,
                                                        K key,
                                                        BiFunctionWithException<? super K,? super V,? extends V,? extends E> mappingFunction) throws E {
        V res;
        if (key==null) {
            throw new IllegalArgumentException("Failure to ensure entry is present in map; key is not set!");
        }
        res=map.get(key);
        if (map.containsKey(key)) {
            V value=mappingFunction.apply(key,res);
            if (value==null) {
                map.remove(key);
            } else {
                map.put(key, value);
            }
            res = value;
        }
        return res;
    }
}
