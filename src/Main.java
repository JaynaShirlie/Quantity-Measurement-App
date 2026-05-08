import java.util.function.DoubleBinaryOperator;

interface IMeasurable {
    double toBase(double value);
    double fromBase(double baseValue);
}

enum LengthUnit implements IMeasurable {
    INCH(1.0),
    FOOT(12.0),
    YARD(36.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

enum WeightUnit implements IMeasurable {
    GRAM(1.0),
    KILOGRAM(1000.0),
    TONNE(1000000.0),
    POUND(453.592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

enum VolumeUnit implements IMeasurable {
    MILLILITRE(1.0),
    LITRE(1000.0);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public <T extends IMeasurable> double convertTo(T targetUnit) {
        if (targetUnit == null || unit.getClass() != targetUnit.getClass()) throw new IllegalArgumentException();
        double base = unit.toBase(value);
        return targetUnit.fromBase(base);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double base = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);
        double result = targetUnit.fromBase(base);
        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double base = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.fromBase(base);
        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performArithmetic(other, null, ArithmeticOperation.DIVIDE);
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {
        if (other == null) throw new IllegalArgumentException();
        if (unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException();
        if (Double.isNaN(other.value) || Double.isInfinite(other.value)) throw new IllegalArgumentException();
        if (targetRequired && targetUnit == null) throw new IllegalArgumentException();
    }

    private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation op) {
        double base1 = unit.toBase(value);
        double base2 = other.unit.toBase(other.value);
        return op.compute(base1, base2);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) throw new ArithmeticException();
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        public double compute(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quantity<?> q)) return false;
        if (unit.getClass() != q.unit.getClass()) return false;
        double base1 = unit.toBase(value);
        double base2 = q.unit.toBase(q.value);
        return Math.abs(base1 - base2) < 0.0001;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Run MainTest for validation");
    }
}