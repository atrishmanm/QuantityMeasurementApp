interface IMeasurable {
    double getConversionFactor();
    String getUnitName();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }
}

// ---------------- LENGTH ----------------
enum LengthUnit implements IMeasurable {
    FEET(1.0), INCHES(1.0 / 12.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }
    public String getUnitName() { return name(); }
}

// ---------------- WEIGHT ----------------
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0), GRAM(0.001);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }
    public String getUnitName() { return name(); }
}

// ---------------- VOLUME ----------------
enum VolumeUnit implements IMeasurable {
    LITRE(1.0), MILLILITRE(0.001), GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }
    public String getUnitName() { return name(); }
}

// ---------------- QUANTITY ----------------
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 0.01;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    // ---------- ENUM FOR OPERATIONS ----------
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final java.util.function.DoubleBinaryOperator op;

        ArithmeticOperation(java.util.function.DoubleBinaryOperator op) {
            this.op = op;
        }

        double apply(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    // ---------- VALIDATION ----------
    private void validate(Quantity<U> other, U targetUnit, boolean checkTarget) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");
        if (!Double.isFinite(value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (checkTarget && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // ---------- CORE HELPER ----------
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return op.apply(base1, base2);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ---------- ADD ----------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    // ---------- SUBTRACT ----------
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    // ---------- DIVIDE ----------
    public double divide(Quantity<U> other) {
        validate(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ---------- CONVERT ----------
    public Quantity<U> convertTo(U targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(result), targetUnit);
    }

    // ---------- EQUALS ----------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}