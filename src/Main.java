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

        public double getValue() {
            return value;
        }

        private double toBaseUnit() {
            return value * unit.getFactor();
        }

        public Length add(Length other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sum = this.toBaseUnit() + other.toBaseUnit();
            double result = sum / targetUnit.getFactor();
            return new Length(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Length)) return false;
            Length other = (Length) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }
    }

    public static void demonstrateAddition(double v1, Length.LengthUnit u1,
                                           double v2, Length.LengthUnit u2,
                                           Length.LengthUnit targetUnit) {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);
        Length result = l1.add(l2, targetUnit);

        System.out.println("Input: Quantity(" + v1 + ", " + u1 + ") + Quantity(" + v2 + ", " + u2 + ")");
        System.out.println("Output: " + result.getValue() + " " + targetUnit);
    }

    public static void main(String[] args) {
        demonstrateAddition(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        demonstrateAddition(1.0, Length.LengthUnit.YARDS, 3.0, Length.LengthUnit.FEET, Length.LengthUnit.YARDS);
        demonstrateAddition(30.48, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.FEET, Length.LengthUnit.FEET);
    }
}