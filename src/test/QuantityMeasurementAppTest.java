package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // -------- WEIGHT TESTS --------

    @Test
    void testWeightEquality_KgAndGram() {
        assertTrue(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
            .equals(new QuantityWeight(1000.0, WeightUnit.GRAM))
        );
    }

    @Test
    void testWeightConversion_PoundToKg() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(0.453592, result.getValue(), 1e-5);
    }

    @Test
    void testWeightConversion_KgToGram() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testWeightAddition_DefaultUnit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(500.0, WeightUnit.GRAM));

        assertEquals(1.5, result.getValue(), EPS);
    }

    @Test
    void testWeightAddition_TargetUnit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM),
                     WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    @Test
    void testWeightAddition_WithPounds() {
        QuantityWeight result =
                new QuantityWeight(2.0, WeightUnit.POUND)
                .add(new QuantityWeight(500.0, WeightUnit.GRAM),
                     WeightUnit.KILOGRAM);

        assertTrue(result.getValue() > 1.3 && result.getValue() < 1.5);
    }

    @Test
    void testWeightInvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    void testWeightNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(1.0, null));
    }
}