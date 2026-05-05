// QuantityTest.java

public class QuantityMeasurementAppTest {

    public static void main(String[] args) {

        // -------- LENGTH --------
        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6, LengthUnit.INCHES);

        System.out.println(l1.subtract(l2)); // 9.5 FEET
        System.out.println(l1.divide(new Quantity<>(2, LengthUnit.FEET))); // 5.0

        // -------- WEIGHT --------
        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000, WeightUnit.GRAM);

        System.out.println(w1.subtract(w2)); // 5 KG
        System.out.println(w1.divide(w2));   // 2.0

        // -------- VOLUME --------
        Quantity<VolumeUnit> v1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

        System.out.println(v1.subtract(v2)); // 4.5 L
        System.out.println(v1.divide(v2));   // 10.0

        // -------- CROSS CATEGORY (ERROR) --------
        try {
            Quantity<LengthUnit> x = new Quantity<>(10, LengthUnit.FEET);
            Quantity<WeightUnit> y = new Quantity<>(5, WeightUnit.KILOGRAM);

            // unsafe cast to simulate misuse
            System.out.println(x.subtract((Quantity) y));
        } catch (Exception e) {
            System.out.println("Cross-category blocked ✔");
        }

        // -------- DIVIDE BY ZERO --------
        try {
            System.out.println(l1.divide(new Quantity<>(0, LengthUnit.FEET)));
        } catch (Exception e) {
            System.out.println("Divide by zero blocked ✔");
        }
    }
}