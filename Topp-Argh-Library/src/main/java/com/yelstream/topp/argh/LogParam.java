package com.yelstream.topp.argh;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

/**
 * <p>
 * Log parameter encapsulating access to a parameter.
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
 * This allows for changing a simple log statements with a parameters which is expensive to evaluate -
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
    /**
     * Constructor.
     * @param textSupplier Supplier of the object whose {@link #toString()} supplies the text to this object.
     */
    protected LogParam(final Supplier<Object> textSupplier) {
        this.textSupplier = textSupplier;
    }

    /**
     * Constructor.
     * @param unprotectedParameter Object whose {@link #toString()} supplies the text to this object.
     */
    protected LogParam(final Object unprotectedParameter) {
        this.textSupplier = () -> unprotectedParameter;
    }

    @Getter
    private final Supplier<Object> textSupplier;

    @Override
    public String toString() {
        String result = null;
        try {
            Object textObject = textSupplier.get();
            if (textObject != null) {
                result = textObject.toString();
            }
        } catch (Exception ex) {
            result = String.format("<Failure to evaluate log parameter; caught exception with message '%s'!>", ex.getMessage());
        }
        return result;
    }

    /**
     * Creates a log parameter.
     * @param textSupplier Supplier of the object whose {@link #toString()} supplies the text to this object.
     */
    public static LogParam of(final Supplier<Object> textSupplier) {
        return new LogParam(textSupplier);
    }

    /**
     * Creates a log parameter.
     * @param unprotectedParameter Object whose {@link #toString()} supplies the text to this object.
     */
    public static LogParam of(final Object unprotectedParameter) {
        return new LogParam(unprotectedParameter);
    }
}
