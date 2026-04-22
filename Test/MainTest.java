import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testAddition_FeetAndInches_ResultFeet() {
        Main.Length l1 = new Main.Length(1.0, Main.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(12.0, Main.LengthUnit.INCHES);
        Main.Length result = l1.add(l2, Main.LengthUnit.FEET);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_YardAndFeet_ResultYard() {
        Main.Length l1 = new Main.Length(1.0, Main.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(3.0, Main.LengthUnit.FEET);
        Main.Length result = l1.add(l2, Main.LengthUnit.YARDS);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_YardAndInches_ResultInches() {
        Main.Length l1 = new Main.Length(1.0, Main.LengthUnit.YARDS);
        Main.Length l2 = new Main.Length(12.0, Main.LengthUnit.INCHES);
        Main.Length result = l1.add(l2, Main.LengthUnit.INCHES);
        assertEquals(48.0, result.getValue());
    }

    @Test
    void testAddition_CmAndFeet_ResultFeet() {
        Main.Length l1 = new Main.Length(30.48, Main.LengthUnit.CENTIMETERS);
        Main.Length l2 = new Main.Length(1.0, Main.LengthUnit.FEET);
        Main.Length result = l1.add(l2, Main.LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_SameUnit() {
        Main.Length l1 = new Main.Length(2.0, Main.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(3.0, Main.LengthUnit.FEET);
        Main.Length result = l1.add(l2, Main.LengthUnit.FEET);
        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAddition_SameReference() {
        Main.Length l1 = new Main.Length(2.0, Main.LengthUnit.FEET);
        Main.Length result = l1.add(l1, Main.LengthUnit.FEET);
        assertEquals(4.0, result.getValue());
    }

    @Test
    void testAddition_NullOther() {
        Main.Length l1 = new Main.Length(1.0, Main.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(null, Main.LengthUnit.FEET);
        });
    }

    @Test
    void testAddition_NullTargetUnit() {
        Main.Length l1 = new Main.Length(1.0, Main.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(1.0, Main.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(l2, null);
        });
    }

    @Test
    void testAddition_ZeroValue() {
        Main.Length l1 = new Main.Length(0.0, Main.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(2.0, Main.LengthUnit.FEET);
        Main.Length result = l1.add(l2, Main.LengthUnit.FEET);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_NegativeValues() {
        Main.Length l1 = new Main.Length(-1.0, Main.LengthUnit.FEET);
        Main.Length l2 = new Main.Length(2.0, Main.LengthUnit.FEET);
        Main.Length result = l1.add(l2, Main.LengthUnit.FEET);
        assertEquals(1.0, result.getValue());
    }

    @Test
    void testAddition_MultipleUnits() {
        Main.Length a = new Main.Length(1.0, Main.LengthUnit.YARDS);
        Main.Length b = new Main.Length(3.0, Main.LengthUnit.FEET);
        Main.Length result = a.add(b, Main.LengthUnit.FEET);
        assertEquals(6.0, result.getValue());
    }

    @Test
    void testAddition_CmAndInches_ResultInches() {
        Main.Length l1 = new Main.Length(2.0, Main.LengthUnit.CENTIMETERS);
        Main.Length l2 = new Main.Length(0.393701, Main.LengthUnit.INCHES);
        Main.Length result = l1.add(l2, Main.LengthUnit.INCHES);
        assertTrue(result.getValue() > 0);
    }
}