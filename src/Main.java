public class Main {

    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public enum LengthUnit {
            FEET(12.0),
            INCHES(1.0),
            YARDS(36.0),
            CENTIMETERS(0.393701);

            private final double factor;

            LengthUnit(double factor) {
                this.factor = factor;
            }

            public double getFactor() {
                return factor;
            }
        }

        public Length(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return value * unit.getFactor(); // convert to inches
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();

            double baseValue = this.toBaseUnit(); // in inches
            return baseValue / targetUnit.getFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || !(obj instanceof Length)) return false;
            Length other = (Length) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }
    }

    public static void demonstrateConversion(double value, Length.LengthUnit from, Length.LengthUnit to) {
        Length length = new Length(value, from);
        double result = length.convertTo(to);

        System.out.println("Input: " + value + " " + from);
        System.out.println("Converted to " + to + ": " + result);
    }

    public static void main(String[] args) {
        demonstrateConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        demonstrateConversion(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
        demonstrateConversion(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);
    }
}