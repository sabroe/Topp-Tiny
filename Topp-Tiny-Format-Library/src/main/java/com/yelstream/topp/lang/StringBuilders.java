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
     * @param builder Builder.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replace(StringBuilder builder,
                              String token,
                              String replacement) {
        int index=builder.indexOf(token);
        if (index!=-1) {
            builder.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces the first occurrence of a string starting from a specific index.
     * @param builder Builder.
     * @param fromIndex Index to start from.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replaceFrom(StringBuilder builder,
                                  int fromIndex,
                                  String token,
                                  String replacement) {
        int index=builder.indexOf(token, fromIndex);
        if (index!=-1) {
            builder.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces the first occurrence of a string.
     * @param builder Builder.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replaceLast(StringBuilder builder,
                                  String token,
                                  String replacement) {
        int index=builder.lastIndexOf(token);
        if (index!=-1) {
            builder.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces the last occurrence of a string starting from a specific index.
     * Note that "last" implies searching backwards.
     * @param builder Builder.
     * @param fromIndex Index to start from.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Index at which a replacement has taken place, if any.
     */
    public static int replaceLastFrom(StringBuilder builder,
                                      int fromIndex,
                                      String token,
                                      String replacement) {
        int index=builder.lastIndexOf(token, fromIndex);
        if (index!=-1) {
            builder.replace(index, index+token.length(), replacement);
        }
        return index;
    }

    /**
     * Replaces all occurrence of a string.
     * @param builder Builder.
     * @param token String to match and replace.
     * @param replacement String to replace with.
     * @return Number of occurrences replaced.
     */
    public static int replaceAll(StringBuilder builder,
                                 String token,
                                 String replacement) {
        int replacedCount=0;
        int indexFrom=0;
        while (indexFrom<builder.length()) {
            int index=builder.indexOf(token, indexFrom);
            if (index==-1) {
                indexFrom=builder.length();
            } else {
                builder.replace(index, index+token.length(), replacement);
                indexFrom=index+token.length();
                replacedCount++;
            }
        }
        return replacedCount;
    }

    /**
     * Appends a token if the buffer is non-empty.
     * @param builder Builder.
     * @param token Token.
     */
    public static void appendTokenIfBuilderIsNonEmpty(StringBuilder builder,
                                                      String token) {
        if (!builder.isEmpty()) {
            builder.append(token);
        }
    }

    /**
     * Appends a delimiter if a given token (to be appended later) does not contain it as a prefix.
     * This does not append the token itself.
     * @param builder Builder
     * @param delimiter Delimiter.
     * @param token Token.
     */
    public static void appendDelimiterIfNotOnToken(StringBuilder builder,
                                                   String delimiter,
                                                   String token) {
        if (!token.startsWith(delimiter)) {
            builder.append(delimiter);
        }
    }

    /**
     * Appends a delimiter if a given token does not contain it as a prefix and together with this same token.
     * @param builder Builder
     * @param delimiter Delimiter.
     * @param token Token.
     */
    public static void appendDelimiterAndToken(StringBuilder builder,
                                               String delimiter,
                                               String token) {
        if (!token.startsWith(delimiter)) {
            builder.append(delimiter);
        }
        builder.append(token);
    }
}
