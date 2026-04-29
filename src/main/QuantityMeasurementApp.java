/**
 * Enum representing supported length units.
 * All conversion factors are relative to base unit = INCHES.
 */
enum LengthUnit {
    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double toInchesFactor;

    LengthUnit(double toInchesFactor) {
        this.toInchesFactor = toInchesFactor;
    }

    public double toInches(double value) {
        return value * toInchesFactor;
    }

    public double fromInches(double inches) {
        return inches / toInchesFactor;
    }
}

/**
 * Immutable value object representing a length.
 */
class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
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

    private double toInches() {
        return unit.toInches(value);
    }

    /**
     * Converts this object to a new unit (returns NEW instance).
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double inches = this.toInches();
        double converted = targetUnit.fromInches(inches);
        return new QuantityLength(converted, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        double diff = Math.abs(this.toInches() - other.toInches());
        return diff < 1e-6;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

/**
 * API class providing conversion and demo methods.
 */
public class QuantityMeasurementApp {

    /**
     * Static conversion API (core UC5 requirement)
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        double inches = source.toInches(value);
        return target.fromInches(inches);
    }

    // ------------------ Method Overloading ------------------

    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
        return result;
    }

    public static double demonstrateLengthConversion(QuantityLength quantity, LengthUnit to) {
        QuantityLength converted = quantity.convertTo(to);
        System.out.println(quantity + " = " + converted);
        return converted.getValue();
    }

    public static boolean demonstrateLengthEquality(QuantityLength a, QuantityLength b) {
        boolean result = a.equals(b);
        System.out.println(a + " == " + b + " ? " + result);
        return result;
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);

        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARDS);
        demonstrateLengthConversion(q, LengthUnit.INCHES);

        demonstrateLengthEquality(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
        );
    }
}