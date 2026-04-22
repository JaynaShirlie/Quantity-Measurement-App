public class Main {

    public enum Unit {
        KILOGRAM {
            public double toBase(double value) { return value * 1000.0; }
            public double fromBase(double base) { return base / 1000.0; }
        },
        GRAM {
            public double toBase(double value) { return value; }
            public double fromBase(double base) { return base; }
        },
        POUND {
            public double toBase(double value) { return value * 453.592; }
            public double fromBase(double base) { return base / 453.592; }
        };

        public abstract double toBase(double value);
        public abstract double fromBase(double base);
    }

    public static class Weight {
        private final double value;
        private final Unit unit;

        public Weight(double value, Unit unit) {
            if (unit == null) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        private double toBase() {
            return unit.toBase(value);
        }

        public Weight add(Weight other, Unit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            double result = targetUnit.fromBase(sum);
            return new Weight(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Weight)) return false;

            Weight other = (Weight) obj;

            double diff = Math.abs(this.toBase() - other.toBase());
            return diff < 0.01;
        }
    }

    public static void main(String[] args) {
        Weight w1 = new Weight(1.0, Unit.KILOGRAM);
        Weight w2 = new Weight(1000.0, Unit.GRAM);
        System.out.println("Equal: " + w1.equals(w2));

        Weight result = w1.add(w2, Unit.KILOGRAM);
        System.out.println("Sum: " + result.getValue() + " KILOGRAM");
    }
}