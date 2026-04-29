package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(2.0, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(new QuantityLength(3.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(36.0, LengthUnit.INCHES)));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertTrue(new QuantityLength(36.0, LengthUnit.INCHES)
                .equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.CENTIMETERS)
                .equals(new QuantityLength(0.393701, LengthUnit.INCHES)));
    }

    @Test
    void testEquality_CentimetersToFeet_NonEquivalentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.CENTIMETERS)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        assertTrue(new QuantityLength(2.0, LengthUnit.YARDS)
                .equals(new QuantityLength(6.0, LengthUnit.FEET)));

        assertTrue(new QuantityLength(6.0, LengthUnit.FEET)
                .equals(new QuantityLength(72.0, LengthUnit.INCHES)));
    }
}