package com.yelstream.topp.util.regex;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * Utility addressing instances of {@link Pattern}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-09
 */
@UtilityClass
public class Patterns {
    /**
     * Group name for matching a pattern within a regular expression {@code *_PARAMETER_REGEX}.
     */
    public static final String PATTERN_GROUP_NAME="pattern";

    /**
     * Group name for matching a key within a regular expression {@code *_PARAMETER_REGEX}.
     */
    public static final String KEY_GROUP_NAME="key";

    /**
     * Group name for matching a begin-delimiter within a regular expression {@code *_PARAMETER_REGEX}.
     */
    public static final String BEGIN_DELIMITER_GROUP_NAME="begin";

    /**
     * Group name for matching an end-delimiter within a regular expression {@code *_PARAMETER_REGEX}.
     */
    public static final String END_DELIMITER_GROUP_NAME="end";

    /**
     * Regular expression matching patterns with variables of the form {@code ${...}}.
     * Variable names match the expression {@code \\w+} identical to {@code [a-zA-Z_0-9]+}.
     * <p>
     *     Note that double {@code $$} serve as an escape with the effect that it is not matched by the expression.
     * </p>
     */
    @SuppressWarnings("java:S5860")
    public static final String VARIABLE_PARAMETER_REGEX=
        "(?<"+PATTERN_GROUP_NAME+">(?<"+BEGIN_DELIMITER_GROUP_NAME+">(^|(?<=[^$]))[$][{])(?<"+KEY_GROUP_NAME+">\\w+)(?<"+END_DELIMITER_GROUP_NAME+">}))";

    /**
     * Creates a pattern for {@link #VARIABLE_PARAMETER_REGEX}.
     * @return Pattern.
     */
    public static Pattern createVariableParameterPattern() {
        return Pattern.compile(VARIABLE_PARAMETER_REGEX);
    }

    /**
     * Regular expression matching arguments used by {@link java.util.Formatter}.
     * <p>
     * While the formatter uses formats of the form {@code %[argument_index$][flags][width][.precision]conversion},
     * this expression matches not only numbers as {@code argument_index},
     * but numbers and names matching the expression {@code \\w+} identical to {@code [a-zA-Z_0-9]+}.
     * </p>
     * <p>
     *     Note that double {@code %%} serve as an escape with the effect that it is not matched by the expression.
     * </p>
     */
    @SuppressWarnings("java:S5860")
    public static final String FORMATTER_PARAMETER_REGEX=
        "(?<"+PATTERN_GROUP_NAME+">(?<"+BEGIN_DELIMITER_GROUP_NAME+">(^|(?<=[^%]))%)(?<"+KEY_GROUP_NAME+">\\w+)(?<"+END_DELIMITER_GROUP_NAME+">[$]))";

    /**
     * Creates a pattern for {@link #FORMATTER_PARAMETER_REGEX}.
     * @return Pattern.
     */
    public static Pattern createFormatterParameterPattern() {
        return Pattern.compile(FORMATTER_PARAMETER_REGEX);
    }
}
