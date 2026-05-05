public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // ---------- ADD ----------
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);

        System.out.println("Add: " + l1.add(l2)); // 2 FEET

        // ---------- SUBTRACT ----------
        Quantity<VolumeUnit> v1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

        System.out.println("Subtract: " + v1.subtract(v2)); // 4.5 L

        // ---------- DIVIDE ----------
        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5, WeightUnit.KILOGRAM);

        System.out.println("Divide: " + w1.divide(w2)); // 2.0

        // ---------- CONVERSION ----------
        System.out.println("Convert: " + v1.convertTo(VolumeUnit.MILLILITRE)); // 5000 mL

        // ---------- EQUALITY ----------
        Quantity<VolumeUnit> v3 = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v4 = new Quantity<>(1, VolumeUnit.LITRE);

        System.out.println("Equal: " + v3.equals(v4)); // true

        // ---------- CROSS CATEGORY ----------
        try {
            System.out.println(l1.subtract((Quantity) w1));
        } catch (Exception e) {
            System.out.println("Cross-category error caught");
        }

        // ---------- DIVIDE BY ZERO ----------
        try {
            Quantity<LengthUnit> zero = new Quantity<>(0, LengthUnit.FEET);
            System.out.println(l1.divide(zero));
        } catch (Exception e) {
            System.out.println("Divide by zero caught");
        }
    }
}