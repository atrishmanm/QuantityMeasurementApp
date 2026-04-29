package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength result = new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength result = new QuantityLength(6, LengthUnit.INCHES)
                .add(new QuantityLength(6, LengthUnit.INCHES));

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength result = new QuantityLength(1, LengthUnit.FEET)
                .add(new QuantityLength(12, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength result = new QuantityLength(12, LengthUnit.INCHES)
                .add(new QuantityLength(1, LengthUnit.FEET));

        assertEquals(24.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1, LengthUnit.YARDS),
                new QuantityLength(3, LengthUnit.FEET),
                LengthUnit.YARDS
        );

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CentimeterPlusInch() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
        );

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    void testAddition_Commutativity() {
        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12, LengthUnit.INCHES);

        double r1 = a.add(b).getValue();
        double r2 = b.add(a).getValue();

        assertEquals(r1, r2, EPS);
    }

    @Test
    void testAddition_WithZero() {
        QuantityLength result = new QuantityLength(5, LengthUnit.FEET)
                .add(new QuantityLength(0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength result = new QuantityLength(5, LengthUnit.FEET)
                .add(new QuantityLength(-2, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_NullOperand() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(1, LengthUnit.FEET).add(null));
    }

    @Test
    void testAddition_LargeValues() {
        QuantityLength result = new QuantityLength(1e6, LengthUnit.FEET)
                .add(new QuantityLength(1e6, LengthUnit.FEET));

        assertEquals(2e6, result.getValue(), EPS);
    }

    @Test
    void testAddition_SmallValues() {
        QuantityLength result = new QuantityLength(0.001, LengthUnit.FEET)
                .add(new QuantityLength(0.002, LengthUnit.FEET));

        assertEquals(0.003, result.getValue(), 1e-6);
    }
}