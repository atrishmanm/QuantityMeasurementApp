package main;

/**
 * Standalone enum responsible for ALL unit conversions.
 * Base unit = FEET
 */
enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /**
     * Convert value in this unit → base unit (feet)
     */
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    /**
     * Convert value from base unit (feet) → this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }
}

/**
 * Immutable value object for length
 */
class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPS = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Convert to another unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(converted, targetUnit);
    }

    /**
     * Addition with explicit target unit (UC7 behavior preserved)
     */
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other operand cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumBase =
                this.unit.convertToBaseUnit(this.value) +
                other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    /**
     * Addition default (result in this unit)
     */
    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    /**
     * Equality based on base unit comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPS;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

/**
 * Demo Application
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Convert: " + q1.convertTo(LengthUnit.INCHES));

        System.out.println("Add (target FEET): " +
                q1.add(q2, LengthUnit.FEET));

        System.out.println("Add (target YARDS): " +
                q1.add(q2, LengthUnit.YARDS));

        System.out.println("Equality: " +
                new QuantityLength(36.0, LengthUnit.INCHES)
                        .equals(new QuantityLength(1.0, LengthUnit.YARDS)));

        System.out.println("Enum direct conversion: " +
                LengthUnit.INCHES.convertToBaseUnit(12.0)); // → 1 foot
    }
}