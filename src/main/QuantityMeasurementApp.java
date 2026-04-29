package main;

/**
 * ---------------- LENGTH SECTION ----------------
 * Base unit = FEET
 */
enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double factor) {
        this.toFeetFactor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}

class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPS = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public QuantityLength convertTo(LengthUnit target) {
        double base = unit.convertToBaseUnit(value);
        return new QuantityLength(target.convertFromBaseUnit(base), target);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        double sumBase =
                unit.convertToBaseUnit(value) +
                other.unit.convertToBaseUnit(other.value);

        return new QuantityLength(target.convertFromBaseUnit(sumBase), target);
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength o = (QuantityLength) obj;

        return Math.abs(
                unit.convertToBaseUnit(value) -
                o.unit.convertToBaseUnit(o.value)
        ) < EPS;
    }

    @Override
    public String toString() {
        return "Length(" + value + ", " + unit + ")";
    }
}


/**
 * ---------------- WEIGHT SECTION (UC9) ----------------
 * Base unit = KILOGRAM
 */
enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double factor) {
        this.toKgFactor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * toKgFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKgFactor;
    }
}

class QuantityWeight {

    private final double value;
    private final WeightUnit unit;
    private static final double EPS = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Conversion
     */
    public QuantityWeight convertTo(WeightUnit target) {
        double base = unit.convertToBaseUnit(value);
        return new QuantityWeight(target.convertFromBaseUnit(base), target);
    }

    /**
     * Addition with explicit target unit
     */
    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null) throw new IllegalArgumentException("Other null");
        if (target == null) throw new IllegalArgumentException("Target null");

        double sumBase =
                unit.convertToBaseUnit(value) +
                other.unit.convertToBaseUnit(other.value);

        return new QuantityWeight(target.convertFromBaseUnit(sumBase), target);
    }

    /**
     * Default addition (this unit)
     */
    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    /**
     * Equality
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight o = (QuantityWeight) obj;

        return Math.abs(
                unit.convertToBaseUnit(value) -
                o.unit.convertToBaseUnit(o.value)
        ) < EPS;
    }

    @Override
    public String toString() {
        return "Weight(" + value + ", " + unit + ")";
    }
}


/**
 * ---------------- DEMO APP ----------------
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // -------- LENGTH --------
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(l1.add(l2, LengthUnit.FEET));   // 2 FEET
        System.out.println(l1.convertTo(LengthUnit.INCHES));

        // -------- WEIGHT --------
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Equal? " + w1.equals(w2)); // true

        System.out.println(
            new QuantityWeight(1.0, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM)
        );

        System.out.println(
            w1.add(w2, WeightUnit.KILOGRAM)
        );

        System.out.println(
            new QuantityWeight(2.0, WeightUnit.POUND)
                .add(new QuantityWeight(500.0, WeightUnit.GRAM), WeightUnit.KILOGRAM)
        );
    }
}