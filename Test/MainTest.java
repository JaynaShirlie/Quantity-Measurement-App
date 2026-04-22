import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.INCHES);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(12.0, Main.Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        Main.Length l1 = new Main.Length(12.0, Main.Length.LengthUnit.INCHES);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(2.0, Main.Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.INCHES);
        Main.Length l2 = new Main.Length(2.0, Main.Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_SameReference() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }

    @Test
    void testEquality_NullComparison() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    @Test
    void testEquality_DifferentClass() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        String str = "invalid";
        assertFalse(l1.equals(str));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Main.Length(1.0, null);
        });
    }
}