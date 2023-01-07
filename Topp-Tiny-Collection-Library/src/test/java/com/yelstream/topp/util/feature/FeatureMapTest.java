package com.yelstream.topp.util.feature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Predicate;

/**
 * Test suite for {@code com.yelstream.topp.feature.FeatureMap}.
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2022-12-17
 */
class FeatureMapTest {

    enum ProductFeature {
        Polling,
        Indexing,
        Notifying,
        Collecting,
        Logging,
        Splitting
    }

    static final FeatureMap<ProductFeature> FEATURE_MAP =
        FeatureMap.of(Map.of(
            ProductFeature.Polling, false,
            ProductFeature.Indexing, false,
            ProductFeature.Notifying, true,
            //No 'Collecting'!
            ProductFeature.Logging, false,
            ProductFeature.Splitting, true
        ));

    /**
     * Tests which features are applicable.
     */
    @Test
    void featureApplicable() {
        FeatureMap<ProductFeature> featureMap=FEATURE_MAP;
        Assertions.assertFalse(featureMap.isFeatureApplicable(null));
        Assertions.assertTrue(featureMap.isFeatureApplicable(ProductFeature.Polling));
        Assertions.assertTrue(featureMap.isFeatureApplicable(ProductFeature.Indexing));
        Assertions.assertTrue(featureMap.isFeatureApplicable(ProductFeature.Notifying));
        Assertions.assertFalse(featureMap.isFeatureApplicable(ProductFeature.Collecting));
    }

    /**
     * Tests the default enabling.
     */
    @Test
    void defaultEnabling() {
        FeatureMap<ProductFeature> featureMap=FEATURE_MAP;
        Assertions.assertFalse(featureMap.isFeatureEnabled(null));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Polling));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Indexing));
        Assertions.assertTrue(featureMap.isFeatureEnabled(ProductFeature.Notifying));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Collecting));
    }

    /**
     * Tests if a feature set is verifiable.
     */
    @Test
    void verifyFeatureEnablingApplicable() {
        FeatureMap<ProductFeature> featureMap=FEATURE_MAP;

        Assertions.assertThrows(IllegalArgumentException.class,()->featureMap.verifyFeatureEnablingApplicable(null));

        {
            Map<ProductFeature, Boolean> featureEnabling =
                    Map.of(ProductFeature.Polling, false,
                            ProductFeature.Indexing, true,
                            ProductFeature.Notifying, false);
            Assertions.assertDoesNotThrow(()->featureMap.verifyFeatureEnablingApplicable(featureEnabling));
        }
        {
            Map<ProductFeature, Boolean> featureEnabling =
                    Map.of(ProductFeature.Polling, false,
                            ProductFeature.Indexing, true,
                            ProductFeature.Collecting, true,
                            ProductFeature.Notifying, false);
            Assertions.assertThrows(IllegalArgumentException.class,()->featureMap.verifyFeatureEnablingApplicable(featureEnabling));
        }
    }

    /**
     * Tests if a feature is applicable.
     */
    @Test
    void featureEnablingApplicable() {
        FeatureMap<ProductFeature> featureMap=FEATURE_MAP;

        Assertions.assertFalse(featureMap.isFeatureEnablingApplicable(null));

        {
            Map<ProductFeature, Boolean> featureEnabling =
                Map.of(ProductFeature.Polling, false,
                       ProductFeature.Indexing, true,
                       ProductFeature.Notifying, false);
            Assertions.assertTrue(featureMap.isFeatureEnablingApplicable(featureEnabling));
        }
        {
            Map<ProductFeature, Boolean> featureEnabling =
                Map.of(ProductFeature.Polling, false,
                       ProductFeature.Indexing, true,
                       ProductFeature.Collecting, true,
                       ProductFeature.Notifying, false);
            Assertions.assertFalse(featureMap.isFeatureEnablingApplicable(featureEnabling));
        }
    }

    /**
     * Tests the default enabling.
     */
    @Test
    void enabling() {
        FeatureMap<ProductFeature> featureMap=FEATURE_MAP;
        Map<ProductFeature,Boolean> featureEnabling=
            Map.of(ProductFeature.Polling,false,
                    ProductFeature.Indexing,true,
                    ProductFeature.Notifying,false);

        Assertions.assertFalse(featureMap.isFeatureEnabled(null));
        Assertions.assertFalse(featureMap.isFeatureEnabled(featureEnabling,null));
        Assertions.assertFalse(featureMap.isFeatureEnabled(null,ProductFeature.Polling));
        Assertions.assertFalse(featureMap.isFeatureEnabled(featureEnabling,ProductFeature.Polling));
        Assertions.assertTrue(featureMap.isFeatureEnabled(featureEnabling,ProductFeature.Indexing));
        Assertions.assertFalse(featureMap.isFeatureEnabled(featureEnabling,ProductFeature.Notifying));
        Assertions.assertFalse(featureMap.isFeatureEnabled(featureEnabling,ProductFeature.Collecting));
        Assertions.assertFalse(featureMap.isFeatureEnabled(featureEnabling,ProductFeature.Logging));
        Assertions.assertTrue(featureMap.isFeatureEnabled(featureEnabling,ProductFeature.Splitting));
    }

    /**
     * Tests the default enabling.
     */
    @Test
    void createdEnabledPredicate() {
        Map<ProductFeature,Boolean> featureEnabling=
                Map.of(ProductFeature.Polling,false,
                        ProductFeature.Indexing,true,
                        ProductFeature.Notifying,false);

        Predicate<ProductFeature> predicate=FEATURE_MAP.createEnabledPredicate(featureEnabling);
        Assertions.assertFalse(predicate.test(ProductFeature.Polling));
        Assertions.assertTrue(predicate.test(ProductFeature.Indexing));
        Assertions.assertFalse(predicate.test(ProductFeature.Notifying));
        Assertions.assertThrows(IllegalArgumentException.class,()->predicate.test(ProductFeature.Collecting));
    }

    /**
     * Tests if a feature is applicable.
     */
    @Test
    void createMapFromEnabling() {
        Map<ProductFeature, Boolean> featureEnabling =
                Map.of(ProductFeature.Polling, false,
                        ProductFeature.Indexing, true,
                        ProductFeature.Notifying, false);
        FeatureMap<ProductFeature> featureMap=FeatureMap.of(FEATURE_MAP,featureEnabling);

        Assertions.assertFalse(featureMap.isFeatureEnabled(null));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Polling));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Polling));
        Assertions.assertTrue(featureMap.isFeatureEnabled(ProductFeature.Indexing));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Notifying));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Collecting));
        Assertions.assertFalse(featureMap.isFeatureEnabled(ProductFeature.Logging));
        Assertions.assertTrue(featureMap.isFeatureEnabled(ProductFeature.Splitting));
    }
}
