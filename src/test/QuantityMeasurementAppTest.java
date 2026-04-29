import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS),
                EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                1e-3);
    }

    @Test
    void testConversion_FeetToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS),
                EPS);
    }

    @Test
    void testConversion_RoundTrip() {
        double v = 5.0;
        double result = QuantityMeasurementApp.convert(
                QuantityMeasurementApp.convert(v, LengthUnit.FEET, LengthUnit.INCHES),
                LengthUnit.INCHES,
                LengthUnit.FEET
        );
        assertEquals(v, result, EPS);
    }

    @Test
    void testConversion_Zero() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_Negative() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    @Test
    void testSameUnitConversion() {
        assertEquals(5.0,
                QuantityMeasurementApp.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPS);
    }
}