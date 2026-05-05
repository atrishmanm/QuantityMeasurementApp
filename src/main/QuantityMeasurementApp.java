interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

// ---------------- LENGTH ----------------
enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() { return name(); }
}

// ---------------- WEIGHT ----------------
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() { return name(); }
}

// ---------------- VOLUME ----------------
enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() { return name(); }
}

// ---------------- GENERIC QUANTITY ----------------
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }

    private void validate(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Null quantity");
        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different categories");
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // -------- EQUALS --------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> q)) return false;

        if (this.unit.getClass() != q.unit.getClass()) return false;

        double v1 = this.unit.convertToBaseUnit(this.value);
        double v2 = ((IMeasurable) q.unit).convertToBaseUnit(q.value);

        return Math.abs(v1 - v2) < 0.0001;
    }

    // -------- CONVERT --------
    public Quantity<U> convertTo(U target) {
        double base = unit.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);
        return new Quantity<>(round(result), target);
    }

    // -------- ADD --------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        validate(other);

        double base = unit.convertToBaseUnit(value)
                + other.unit.convertToBaseUnit(other.value);

        return new Quantity<>(round(target.convertFromBaseUnit(base)), target);
    }

    // -------- SUBTRACT --------
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validate(other);
        if (target == null) throw new IllegalArgumentException();

        double base = unit.convertToBaseUnit(value)
                - other.unit.convertToBaseUnit(other.value);

        return new Quantity<>(round(target.convertFromBaseUnit(base)), target);
    }

    // -------- DIVIDE --------
    public double divide(Quantity<U> other) {
        validate(other);

        double divisor = other.unit.convertToBaseUnit(other.value);
        if (divisor == 0) throw new ArithmeticException("Divide by zero");

        return unit.convertToBaseUnit(value) / divisor;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}