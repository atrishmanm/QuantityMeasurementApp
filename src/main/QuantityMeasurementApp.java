// File: QuantityMeasurementApp.java

interface IMeasurable {
    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();
}

// -------- Length Unit --------
enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}

// -------- Weight Unit --------
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(1.0 / 1000.0),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}

// -------- Generic Quantity --------
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPS = 1e-6;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U target) {
        if (target == null) throw new IllegalArgumentException();

        double base = unit.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);

        return new Quantity<>(round(result), target);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        if (other == null || target == null) throw new IllegalArgumentException();

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different categories");
        }

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;
        double result = target.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), target);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quantity<?>)) return false;

        Quantity<?> q = (Quantity<?>) o;

        if (!unit.getClass().equals(q.unit.getClass())) return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) q.unit).convertToBaseUnit(q.value);

        return Math.abs(base1 - base2) < EPS;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}

// -------- Main App --------
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Length
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(l1.equals(l2)); // true
        System.out.println(l1.convertTo(LengthUnit.INCHES));
        System.out.println(l1.add(l2, LengthUnit.FEET));

        // Weight
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2)); // true
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        System.out.println(w1.add(w2, WeightUnit.KILOGRAM));

        // Cross-category
        System.out.println(l1.equals((Object) w1)); // false
    }
}