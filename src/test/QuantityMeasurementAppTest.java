package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // ---------- ENUM TESTS ----------

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPS);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPS);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPS);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                1e-2);
    }

    // ---------- QUANTITY TESTS ----------

    @Test
    void testEquality_CrossUnit() {
        assertTrue(
            new QuantityLength(1.0, LengthUnit.FEET)
            .equals(new QuantityLength(12.0, LengthUnit.INCHES))
        );
    }

    @Test
    void testConvertTo() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, q.getValue(), EPS);
    }

    @Test
    void testAddition_DefaultUnit() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetUnit_Yards() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES),
                     LengthUnit.YARDS);

        assertEquals(0.666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_TargetUnit_Feet() {
        QuantityLength result =
                new QuantityLength(36.0, LengthUnit.INCHES)
                .add(new QuantityLength(1.0, LengthUnit.YARDS),
                     LengthUnit.FEET);

        assertEquals(6.0, result.getValue(), EPS);
    }

    @Test
    void testNullUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(1.0, null));
    }

    @Test
    void testInvalidValue_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testRoundTripConversion() {
        QuantityLength q = new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength result =
                q.convertTo(LengthUnit.INCHES)
                 .convertTo(LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), EPS);
    }
}