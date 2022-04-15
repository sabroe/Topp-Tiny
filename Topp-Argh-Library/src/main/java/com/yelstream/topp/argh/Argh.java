package com.yelstream.topp.argh;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
public final class Argh {
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
}
