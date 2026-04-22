public class Main {

    public enum LengthUnit {
        FEET {
            public double toInches(double value) { return value * 12.0; }
            public double fromInches(double inches) { return inches / 12.0; }
        },
        INCHES {
            public double toInches(double value) { return value; }
            public double fromInches(double inches) { return inches; }
        },
        YARDS {
            public double toInches(double value) { return value * 36.0; }
            public double fromInches(double inches) { return inches / 36.0; }
        },
        CENTIMETERS {
            public double toInches(double value) { return value * 0.393701; }
            public double fromInches(double inches) { return inches / 0.393701; }
        };

        public abstract double toInches(double value);
        public abstract double fromInches(double inches);
    }

    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        private double toBaseUnit() {
            return unit.toInches(value);
        }

        public Length add(Length other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sumInInches = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.fromInches(sumInInches);
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

    public static void main(String[] args) {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.FEET);
        System.out.println("Result: " + result.getValue() + " FEET");
    }
}