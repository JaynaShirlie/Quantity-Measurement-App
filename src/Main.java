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

        public Length add(Length other, LengthUnit resultUnit) {
            if (other == null || resultUnit == null) throw new IllegalArgumentException();
            double sum = this.toBaseUnit() + other.toBaseUnit();
            double result = sum / resultUnit.getFactor();
            return new Length(result, resultUnit);
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

    public static void main(String[] args) {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length result = l1.add(l2, Length.LengthUnit.FEET);

        System.out.println("Result: " + result.getValue() + " FEET");
    }
}