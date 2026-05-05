import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityManagementAppTest {

    // ===== LENGTH =====
    @Test
    void testLengthEquality() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES), result.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES));
    }

    // ===== WEIGHT =====
    @Test
    void testWeightEquality() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    // ===== VOLUME =====
    @Test
    void testVolumeEquality() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(a.equals(b));
    }

    @Test
    void testVolumeAddition() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = a.add(b);
        assertTrue(result.equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    // ===== CROSS CATEGORY =====
    @Test
    void testCrossCategory() {
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(l.equals(w));
    }
}