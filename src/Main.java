public class Main {

    // Inner class to represent Feet measurement
    public static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // 1. Reference Check
            if (this == obj) {
                return true;
            }

            // 2. Null Check
            if (obj == null) {
                return false;
            }

            // 3. Type Check
            if (!(obj instanceof Feet)) {
                return false;
            }

            // 4. Value Comparison
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + f1.equals(f2) + ")");
    }
}