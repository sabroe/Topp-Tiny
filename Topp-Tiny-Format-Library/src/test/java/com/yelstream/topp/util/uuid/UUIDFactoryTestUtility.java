package com.yelstream.topp.util.uuid;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * Utility helping test UUID factories.
 */
@UtilityClass
public class UUIDFactoryTestUtility {
    /**
     * .
     * @param g .
     * @param listSize .
     * @return .
     */
    public static UUID[] createUUIDList(UUIDFactory g,
                                        int listSize) {
        UUID[] l=new UUID[listSize];
        for (int i=0; i<listSize; i++) {
            l[i]=g.createUUID();
        }
        return l;
    }
}
