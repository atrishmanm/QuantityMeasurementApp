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
 * Immutable Value Object
 */
class QuantityLength {
    private final double value;
    private final LengthUnit unit;
    private static final double EPS = 1e-6;

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
        return new QuantityLength(target.fromInches(inches), target);
    }

    // 🔥 UC7 CORE: Private helper (DRY)
    private static QuantityLength addInternal(QuantityLength a,
                                              QuantityLength b,
                                              LengthUnit target) {

        double sumInches = a.toInches() + b.toInches();
        double result = target.fromInches(sumInches);

        return new QuantityLength(result, target);
    }

    /**
     * UC6 (backward compatible): result in first operand unit
     */
    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException("Other null");
        return addInternal(this, other, this.unit);
    }

    /**
     * UC7: explicit target unit (MAIN METHOD)
     */
    public static QuantityLength add(QuantityLength a,
                                    QuantityLength b,
                                    LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return addInternal(a, b, targetUnit);
    }

    /**
     * Overloaded version (raw inputs)
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
        return Math.abs(this.toInches() - other.toInches()) < EPS;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

/**
 * Demo App
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
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
            )
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
            )
        );
    }
}