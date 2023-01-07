package com.yelstream.topp.util.uuid;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
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

    /**
     * .
     * @param l .
     * @return .
     */
    public static String[] convert(UUID[] l) {
        String[] res=new String[l.length];
        for (int i=0; i<l.length; i++) {
            res[i]=l[i].toString();
        }
        return res;
    }

    /**
     * .
     * @param l .
     */
    public static void verifyUUIDList(UUID[] l) {
        String[] l2=convert(l);

        Arrays.sort(l2);

        for (int i=0; i<l2.length-1; i++) {
            String x=l2[i];
            String y=l2[i+1];

            assert x.compareTo(y)<0;
        }
    }
}
