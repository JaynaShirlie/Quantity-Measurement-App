public class Main {

    public interface IMeasurable {
        double getConversionFactor();
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }

    public enum LengthUnit implements IMeasurable {
        FEET(1.0), INCHES(1.0 / 12.0);

        private final double factor;

        LengthUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }

        public double convertToBaseUnit(double value) { return value * factor; }

        public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }

        public String getUnitName() { return name(); }
    }

    public enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0), GRAM(0.001);

        private final double factor;

        WeightUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }

        public double convertToBaseUnit(double value) { return value * factor; }

        public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }

        public String getUnitName() { return name(); }
    }

    public enum VolumeUnit implements IMeasurable {
        LITRE(1.0), MILLILITRE(0.001), GALLON(3.78541);

        private final double factor;

        VolumeUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }

        public double convertToBaseUnit(double value) { return value * factor; }

        public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }

        public String getUnitName() { return name(); }
    }

    public static class Quantity<U extends IMeasurable> {
        private final double value;
        private final U unit;
        private static final double EPSILON = 0.0001;

        public Quantity(double value, U unit) {
            if (unit == null) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() { return value; }

        public U getUnit() { return unit; }

        private void validate(Quantity<U> other) {
            if (other == null) throw new IllegalArgumentException();
            if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException();
        }

        private double toBase() { return unit.convertToBaseUnit(value); }

        public Quantity<U> convertTo(U targetUnit) {
            double base = toBase();
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity<>(round(converted), targetUnit);
        }

        public Quantity<U> add(Quantity<U> other) {
            return add(other, unit);
        }

        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            validate(other);
            double result = this.toBase() + other.toBase();
            return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
        }

        public Quantity<U> subtract(Quantity<U> other) {
            return subtract(other, unit);
        }

        public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
            validate(other);
            if (targetUnit == null) throw new IllegalArgumentException();
            double result = this.toBase() - other.toBase();
            return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
        }

        public double divide(Quantity<U> other) {
            validate(other);
            double divisor = other.toBase();
            if (divisor == 0) throw new ArithmeticException();
            return this.toBase() / divisor;
        }

        private double round(double val) {
            return Math.round(val * 100.0) / 100.0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Quantity<?> q)) return false;
            if (!unit.getClass().equals(q.unit.getClass())) return false;
            return Math.abs(this.toBase() - q.unit.convertToBaseUnit(q.value)) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBase());
        }

        @Override
        public String toString() {
            return value + " " + unit.getUnitName();
        }
    }

    public static void main(String[] args) {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        System.out.println(a.subtract(b));
        System.out.println(a.divide(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }
}