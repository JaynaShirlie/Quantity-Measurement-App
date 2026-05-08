public class Main {

    interface IMeasurable {
        double getConversionFactor();
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }

    enum LengthUnit implements IMeasurable {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return round(value * conversionFactor);
        }

        public double convertFromBaseUnit(double baseValue) {
            return round(baseValue / conversionFactor);
        }

        public String getUnitName() {
            return this.name();
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    enum WeightUnit implements IMeasurable {
        MILLIGRAM(0.001),
        GRAM(1.0),
        KILOGRAM(1000.0),
        POUND(453.592),
        TONNE(1_000_000.0);

        private final double conversionFactor;

        WeightUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return round(value * conversionFactor);
        }

        public double convertFromBaseUnit(double baseValue) {
            return round(baseValue / conversionFactor);
        }

        public String getUnitName() {
            return this.name();
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    enum VolumeUnit implements IMeasurable {
        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double conversionFactor;

        VolumeUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return round(value * conversionFactor);
        }

        public double convertFromBaseUnit(double baseValue) {
            return round(baseValue / conversionFactor);
        }

        public String getUnitName() {
            return this.name();
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    static class Quantity<U extends IMeasurable> {
        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (!Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public U getUnit() {
            return unit;
        }

        public Quantity<U> convertTo(U targetUnit) {
            if (unit.getClass() != targetUnit.getClass())
                throw new IllegalArgumentException();
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity<>(converted, targetUnit);
        }

        public Quantity<U> add(Quantity<U> other) {
            return add(other, this.unit);
        }

        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            if (unit.getClass() != other.unit.getClass())
                throw new IllegalArgumentException();
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            double sumBase = base1 + base2;
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new Quantity<>(result, targetUnit);
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Quantity<?>)) return false;
            Quantity<?> other = (Quantity<?>) obj;
            if (this.unit.getClass() != other.unit.getClass())
                return false;
            double base1 = unit.convertToBaseUnit(value);
            double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);
            return Double.compare(base1, base2) == 0;
        }

        public int hashCode() {
            double base = unit.convertToBaseUnit(value);
            return Double.hashCode(base);
        }

        public String toString() {
            return "Quantity(" + value + ", " + unit.getUnitName() + ")";
        }
    }

    static class QuantityMeasurementApp {
        public static <U extends IMeasurable> boolean demonstrateEquality(
                Quantity<U> q1, Quantity<U> q2) {
            return q1.equals(q2);
        }

        public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
                Quantity<U> quantity, U targetUnit) {
            return quantity.convertTo(targetUnit);
        }

        public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
                Quantity<U> q1, Quantity<U> q2) {
            return q1.add(q2);
        }

        public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
                Quantity<U> q1, Quantity<U> q2, U targetUnit) {
            return q1.add(q2, targetUnit);
        }
    }

    public static void main(String[] args) {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println(v1.equals(v2));
        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println(v1.add(v2, VolumeUnit.LITRE));
        System.out.println(v3.convertTo(VolumeUnit.LITRE));
    }
}