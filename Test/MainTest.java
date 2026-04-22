import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // Test: same values → should be equal
    @Test
    void testEquality_SameValue() {
        Main.Feet f1 = new Main.Feet(1.0);
        Main.Feet f2 = new Main.Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    // Test: different values → should NOT be equal
    @Test
    void testEquality_DifferentValue() {
        Main.Feet f1 = new Main.Feet(1.0);
        Main.Feet f2 = new Main.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    // Test: comparing with null → should be false
    @Test
    void testEquality_NullComparison() {
        Main.Feet f1 = new Main.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    // Test: comparing with different class → should be false
    @Test
    void testEquality_NonNumericInput() {
        Main.Feet f1 = new Main.Feet(1.0);
        String str = "not a number";

        assertFalse(f1.equals(str));
    }

    // Test: same reference → should be true
    @Test
    void testEquality_SameReference() {
        Main.Feet f1 = new Main.Feet(1.0);

        assertTrue(f1.equals(f1));
    }
}