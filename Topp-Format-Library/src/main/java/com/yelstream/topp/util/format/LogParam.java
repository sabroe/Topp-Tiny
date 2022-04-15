package com.yelstream.topp.util.format;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

/**
 * <p>
 * Encapsulated access to a parameter eventually evaluated by its {@link #toString()} method.
 * This provides lazy evaluation and protected evaluation in relation to occurrences of exceptions.
 * </p>
 *
 * <p>
 * This allows for changing a multiline log statement -
 * </p>
 * <pre>
 *   if (log.isDebugEnabled()) {
 *       log.debug("Created message; formatted message is -\n{}.", ObjectMappers.format(message));
 *   }
 * </pre>
 * <p>
 * - into a single line:
 * </p>
 * <pre>
 *   log.debug("Created message; formatted message is -\n{}.", LogParam.of(()->ObjectMappers.format(message)));
 * </pre>
 *
 * <p>
 * This allows for changing a simple log statement with a parameter expensive to evaluate -
 * </p>
 * <pre>
 *     log.debug("Consuming record from topic; record is '{}'.", record);
 * </pre>
 * <p>
 * - into a statement where the parameter is evaluated only if needed:
 * </p>
 * <pre>
 *     log.debug("Consuming record from topic; record is '{}'.", LogParam.of(record));
 * </pre>
 */
@AllArgsConstructor
public final class LogParam {
    private final Supplier<Object> textSupplier;

    @Override
    public String toString() {
        String result=null;
        try {
            Object textObject=textSupplier.get();
            if (textObject!=null) {
                result=textObject.toString();
            }
        } catch (Exception ex) {
            result=String.format("<Failure to evaluate parameter; caught exception with message '%s'!>", ex.getMessage());
        }
        return result;
    }

    /**
     * Creates a parameter.
     * @param textSupplier Supplier of the object whose {@link #toString()} supplies the text to this object.
     */
    public static LogParam of(final Supplier<Object> textSupplier) {
        return new LogParam(textSupplier);
    }

    /**
     * Creates a parameter.
     * @param unprotectedParameter Object whose {@link #toString()} supplies the text to this object.
     */
    public static LogParam of(final Object unprotectedParameter) {
        return of(()->unprotectedParameter);
    }
}
