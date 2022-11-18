package com.yelstream.topp.lang;

import lombok.experimental.UtilityClass;

/**
 * Utility addressing instances of {@link StringBuilder}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-11-18
 */
@UtilityClass
@SuppressWarnings("unused")
public class StringBuilders {
    /**
     * Replaces the first occurrence of a string.
     * @param buffer Buffer.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replace(StringBuilder buffer,
                              String token,
                              String replacement) {
        int index=buffer.indexOf(token);
        if (index!=-1) {
            buffer.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces the first occurrence of a string starting from a specific index.
     * @param buffer Buffer.
     * @param fromIndex Index to start from.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replaceFrom(StringBuilder buffer,
                                  int fromIndex,
                                  String token,
                                  String replacement) {
        int index=buffer.indexOf(token, fromIndex);
        if (index!=-1) {
            buffer.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces the first occurrence of a string.
     * @param buffer Buffer.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replaceLast(StringBuilder buffer,
                                  String token,
                                  String replacement) {
        int index=buffer.lastIndexOf(token);
        if (index!=-1) {
            buffer.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces the last occurrence of a string starting from a specific index.
     * @param buffer Buffer.
     * @param fromIndex Index to start from.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replaceLastFrom(StringBuilder buffer,
                                      int fromIndex,
                                      String token,
                                      String replacement) {
        int index=buffer.lastIndexOf(token, fromIndex);
        if (index!=-1) {
            buffer.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces all occurrence of a string.
     * @param buffer Buffer.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     */
    public static void replaceAll(StringBuilder buffer,
                                  String token,
                                  String replacement) {
        int indexFrom=0;
        while (indexFrom<buffer.length()) {
            int index=buffer.indexOf(token, indexFrom);
            if (index==-1) {
                indexFrom=buffer.length();
            } else {
                buffer.replace(index, index+token.length(), replacement);
                indexFrom=index+token.length();
            }
        }
    }
}
