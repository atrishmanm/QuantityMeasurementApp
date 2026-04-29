package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-3;

    @Test
    void testAddition_Target_Feet() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1, LengthUnit.FEET),
                new QuantityLength(12, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertEquals(2.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_Target_Inches() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1, LengthUnit.FEET),
                new QuantityLength(12, LengthUnit.INCHES),
                LengthUnit.INCHES);

        assertEquals(24.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_Target_Yards() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1, LengthUnit.FEET),
                new QuantityLength(12, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(0.666, r.getValue(), EPS);
    }

    @Test
    void testAddition_Target_Centimeters() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1, LengthUnit.INCHES),
                new QuantityLength(1, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);

        assertEquals(5.08, r.getValue(), EPS);
    }

    @Test
    void testAddition_Commutativity_WithTarget() {
        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12, LengthUnit.INCHES);

        double r1 = QuantityLength.add(a, b, LengthUnit.YARDS).getValue();
        double r2 = QuantityLength.add(b, a, LengthUnit.YARDS).getValue();

        assertEquals(r1, r2, EPS);
    }

    @Test
    void testAddition_WithZero_TargetYards() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(5, LengthUnit.FEET),
                new QuantityLength(0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(1.667, r.getValue(), EPS);
    }

    @Test
    void testAddition_Negative_TargetInches() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(5, LengthUnit.FEET),
                new QuantityLength(-2, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(36.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_NullTarget() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1, LengthUnit.FEET),
                        new QuantityLength(1, LengthUnit.FEET),
                        null));
    }

    @Test
    void testAddition_LargeToSmall() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1000, LengthUnit.FEET),
                new QuantityLength(500, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(18000.0, r.getValue(), EPS);
    }

    @Test
    void testAddition_SmallToLarge() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(12, LengthUnit.INCHES),
                new QuantityLength(12, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(0.667, r.getValue(), EPS);
    }
}