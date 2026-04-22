public class Main {

    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public enum LengthUnit {
            FEET(12.0),
            INCHES(1.0);

            private final double conversionFactor;

            LengthUnit(double conversionFactor) {
                this.conversionFactor = conversionFactor;
            }

            public double getConversionFactor() {
                return conversionFactor;
            }
        }

        public Length(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        private boolean compare(Length other) {
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Length)) return false;
            Length other = (Length) obj;
            return compare(other);
        }
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static void demonstrateFeetEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println("Input: Quantity(1.0, feet) and Quantity(1.0, feet)");
        System.out.println("Output: Equal (" + demonstrateLengthEquality(l1, l2) + ")");
    }

    public static void demonstrateInchesEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        System.out.println("Input: Quantity(1.0, inch) and Quantity(1.0, inch)");
        System.out.println("Output: Equal (" + demonstrateLengthEquality(l1, l2) + ")");
    }

    public static void demonstrateFeetInchesComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println("Input: Quantity(1.0, feet) and Quantity(12.0, inches)");
        System.out.println("Output: Equal (" + demonstrateLengthEquality(l1, l2) + ")");
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}