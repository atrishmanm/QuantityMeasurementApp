package main;

/**
 * Enum for length units (base = INCHES)
 */
enum LengthUnit {
    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double toInchesFactor;

    LengthUnit(double factor) {
        this.toInchesFactor = factor;
    }

    public double toInches(double value) {
        return value * toInchesFactor;
    }

    public double fromInches(double inches) {
        return inches / toInchesFactor;
    }
}

/**
 * Immutable value object
 */
class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public LengthUnit getUnit() { return unit; }

    private double toInches() {
        return unit.toInches(value);
    }

    public QuantityLength convertTo(LengthUnit target) {
        if (target == null) throw new IllegalArgumentException("Target unit null");

        double inches = toInches();
        double converted = target.fromInches(inches);
        return new QuantityLength(converted, target);
    }

    /**
     * UC6: Addition (instance method)
     * Result in THIS object's unit
     */
    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException("Other cannot be null");

        double sumInches = this.toInches() + other.toInches();
        double result = this.unit.fromInches(sumInches);

        return new QuantityLength(result, this.unit);
    }

    /**
     * Static addition with target unit
     */
    public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {
        if (a == null || b == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit null");

        double sumInches = a.toInches() + b.toInches();
        double result = targetUnit.fromInches(sumInches);

        return new QuantityLength(result, targetUnit);
    }

    /**
     * Overloaded version (raw values)
     */
    public static QuantityLength add(double v1, LengthUnit u1,
                                     double v2, LengthUnit u2,
                                     LengthUnit target) {

        return add(new QuantityLength(v1, u1),
                   new QuantityLength(v2, u2),
                   target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toInches() - other.toInches()) < 1e-6;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

/**
 * Demo API class
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
            )
        );

        System.out.println(
            new QuantityLength(12.0, LengthUnit.INCHES)
                .add(new QuantityLength(1.0, LengthUnit.FEET))
        );

        System.out.println(
            QuantityLength.add(1.0, LengthUnit.YARDS,
                               3.0, LengthUnit.FEET,
                               LengthUnit.YARDS)
        );
    }
}