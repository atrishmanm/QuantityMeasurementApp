package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.QuantityMeasurementApp;

public class QuantityMeasurementAppTest {

    // ----------- FEET TESTS -----------

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testFeetEquality_DifferentClass() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals("1.0"));
    }

    // ----------- INCHES TESTS -----------

    @Test
    public void testInchesEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    @Test
    public void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(i.equals(i));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testInchesEquality_DifferentClass() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals(100));
    }
}