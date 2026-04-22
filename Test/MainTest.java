import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(2.0, Main.Length.LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(3.0, Main.Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        Main.Length l1 = new Main.Length(3.0, Main.Length.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(36.0, Main.Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        Main.Length l1 = new Main.Length(36.0, Main.Length.LengthUnit.INCHES);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(2.0, Main.Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.CENTIMETERS);
        Main.Length l2 = new Main.Length(0.393701, Main.Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        Main.Length l1 = new Main.Length(1.0, Main.Length.LengthUnit.CENTIMETERS);
        Main.Length l2 = new Main.Length(1.0, Main.Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Main.Length a = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        Main.Length b = new Main.Length(3.0, Main.Length.LengthUnit.FEET);
        Main.Length c = new Main.Length(36.0, Main.Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_YardSameReference() {
        Main.Length l = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        assertTrue(l.equals(l));
    }

    @Test
    void testEquality_YardNullComparison() {
        Main.Length l = new Main.Length(1.0, Main.Length.LengthUnit.YARDS);
        assertFalse(l.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Main.Length(1.0, null);
        });
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Main.Length a = new Main.Length(2.0, Main.Length.LengthUnit.YARDS);
        Main.Length b = new Main.Length(6.0, Main.Length.LengthUnit.FEET);
        Main.Length c = new Main.Length(72.0, Main.Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }
}