package main;

enum LengthUnit {
    FEET(12.0),          // base = inches
    INCHES(1.0),
    YARDS(36.0),         // 1 yard = 36 inches
    CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

    private final double toInchesFactor;

    LengthUnit(double toInchesFactor) {
        this.toInchesFactor = toInchesFactor;
    }

    public double toInches(double value) {
        return value * toInchesFactor;
    }
}

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toInches() {
        return unit.toInches(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        // Compare with tolerance for floating-point precision
        double diff = Math.abs(this.toInches() - other.toInches());
        return diff < 0.0001;
    }
}

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("1 yard == 3 feet ? " + q1.equals(q2));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q4 = new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println("1 cm == 0.393701 inches ? " + q3.equals(q4));
    }
}