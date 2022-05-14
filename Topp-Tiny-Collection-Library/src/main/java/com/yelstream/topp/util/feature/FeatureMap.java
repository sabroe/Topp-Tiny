package com.yelstream.topp.util.feature;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Map of which features are applicable together with the default enabling of each individual feature.
 * <p>
 *     Example of how to declare a feature map using some specific features:
 * </p>
 * <pre>
 * enum ProductFeature {
 *     Polling,
 *     Indexing,
 *     Notifying,
 *     Collecting
 * }
 *
 * static final FeatureMap&lt;ProductFeature&gt; FEATURE_MAP =
 *     FeatureMap.of(Map.of(
 *         ProductFeature.Polling, false,
 *         ProductFeature.Indexing, false,
 *         ProductFeature.Notifying, true
 *     ));
 * </pre>
 * <p>
 *     Example of how to use and test for a specific feature within a receiving method:
 * </p>
 * <pre>
 * void process(FeatureMap&lt;ProductFeature&gt; featureMap,
 *              Map&lt;ProductFeature,Boolean&gt; featureEnabling,
 *              ...) {
 *     Predicate&lt;ProducerFeature&gt; enabled=featureMap.createEnabledPredicate(featureEnabling);
 *     if (enabled.test(ProductFeature.Notifying) {
 *         ...
 *     }
 *     ...
 * }
 * </pre>
 * <p>
 *     The argument {@code featureMap} is the valid features and their default values while
 *     the argument {@code featureEnabling} is the actually set and overriding values.
 * </p>
 * @param <F> Type of feature.
 *
 * @author Morten Sabroe Mortenen
 * @version 1.0
 * @since 2022-05-14
 */
public class FeatureMap<F extends Enum<F>> {

    /**
     * Associates applicable features with their default enabling.
     * A feature is recognized if and only if it is a key in this map.
     * Default values may be any of {@code null}, {@code Boolean.FALSE} and {@code Boolean.TRUE}.
     * This is immutable.
     */
    @Getter
    private final Map<F,Boolean> defaultFeatureEnabling;

    /**
     * Constructor.
     * @param defaultFeatureEnabling Association of applicable features with their default enabling.
     */
    public FeatureMap(Map<F,Boolean> defaultFeatureEnabling) {
        this.defaultFeatureEnabling=Collections.unmodifiableMap(defaultFeatureEnabling);
    }

    /**
     * Indicates, if a specific feature is applicable.
     * @param feature Feature.
     * @return Indicates, if feature is applicable.
     */
    public boolean isFeatureApplicable(F feature) {
        return feature!=null && defaultFeatureEnabling.containsKey(feature);
    }

    /**
     * Indicates, if a specific set of features are applicable.
     * @param featureEnabling Actual features with their enabling.
     * @return Indicates, if feature is applicable.
     */
    public boolean isFeatureEnablingApplicable(Map<F,Boolean> featureEnabling) {
        return featureEnabling==null || defaultFeatureEnabling.keySet().containsAll(featureEnabling.keySet());
    }

    /**
     * Verifies that a feature is applicable.
     * If the feature is not applicable then an exception is thrown.
     * @param feature Feature.
     */
    public void verifyFeatureApplicable(F feature) {
        if (!isFeatureApplicable(feature)) {
            throw new IllegalArgumentException(String.format("Failure to recognize feature; feature is %s, recognized features are %s!",feature,defaultFeatureEnabling));
        }
    }

    /**
     * Indicates, if a specific feature is enabled relative to default feature enabling.
     * @param feature Feature to test enabling of.
     * @return Indicates, if feature is enabled.
     */
    public boolean isFeatureEnabled(F feature) {
        boolean enabled=false;
        if (defaultFeatureEnabling.containsKey(feature)) {
            enabled=Boolean.TRUE==defaultFeatureEnabling.get(feature);
        }
        return enabled;
    }

    /**
     * Indicates, if a specific feature is enabled relative to actual and default feature enabling.
     * @param featureEnabling Actual features with their enabling.
     * @param feature Feature to test enabling of.
     * @return Indicates, if feature is enabled.
     */
    public boolean isFeatureEnabled(Map<F,Boolean> featureEnabling,
                                    F feature) {
        boolean enabled=false;
        if (featureEnabling!=null && featureEnabling.containsKey(feature)) {
            enabled=Boolean.TRUE==featureEnabling.get(feature);
        } else {
            if (defaultFeatureEnabling.containsKey(feature)) {
                enabled=Boolean.TRUE==defaultFeatureEnabling.get(feature);
            }
        }
        return enabled;
    }

    /**
     * Indicates, if a specific feature is enabled relative to actual and default feature enabling.
     * @param featureEnabling Actual features with their enabling.
     * @param feature Feature to test enabling of.
     * @return Indicates, if feature is enabled.
     */
    public boolean isFeatureApplicableAndEnabled(Map<F,Boolean> featureEnabling,
                                                 F feature) {
        verifyFeatureApplicable(feature);
        return isFeatureEnabled(featureEnabling,feature);
    }

    /**
     * Creates a predicate indicating if a specific feature is enabled.
     * @param featureEnabling Actual features with their enabling.
     * @return Predicate indicating if a specific feature is enabled.
     */
    public Predicate<F> createEnabledPredicate(Map<F,Boolean> featureEnabling) {
        return feature->isFeatureApplicableAndEnabled(featureEnabling,feature);
    }

    /**
     * Factory method.
     * @param defaultFeatureEnabling Association of applicable features with their default enabling.
     * @param <F> Type of feature.
     * @return Created instance.
     */
    public static <F extends Enum<F>> FeatureMap<F> of(Map<F, Boolean> defaultFeatureEnabling) {
        return new FeatureMap<>(defaultFeatureEnabling);
    }
}
