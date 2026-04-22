import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testEquality_KgToKg_SameValue() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        Main.Weight w2 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KgToGram_Equivalent() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        Main.Weight w2 = new Main.Weight(1000.0, Main.Unit.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_GramToKg_Equivalent() {
        Main.Weight w1 = new Main.Weight(1000.0, Main.Unit.GRAM);
        Main.Weight w2 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KgToPound_Equivalent() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        Main.Weight w2 = new Main.Weight(2.20462, Main.Unit.POUND);
        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_DifferentValue() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        Main.Weight w2 = new Main.Weight(2.0, Main.Unit.KILOGRAM);
        assertFalse(w1.equals(w2));
    }

    @Test
    void testAddition_KgAndGram() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        Main.Weight w2 = new Main.Weight(500.0, Main.Unit.GRAM);
        Main.Weight result = w1.add(w2, Main.Unit.KILOGRAM);
        assertEquals(1.5, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_PoundAndKg() {
        Main.Weight w1 = new Main.Weight(2.20462, Main.Unit.POUND);
        Main.Weight w2 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        Main.Weight result = w1.add(w2, Main.Unit.KILOGRAM);
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testAddition_SameUnit() {
        Main.Weight w1 = new Main.Weight(2.0, Main.Unit.GRAM);
        Main.Weight w2 = new Main.Weight(3.0, Main.Unit.GRAM);
        Main.Weight result = w1.add(w2, Main.Unit.GRAM);
        assertEquals(5.0, result.getValue());
    }

    @Test
    void testNullComparison() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        assertFalse(w1.equals(null));
    }

    @Test
    void testSameReference() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        assertTrue(w1.equals(w1));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Main.Weight(1.0, null);
        });
    }

    @Test
    void testAddition_Null() {
        Main.Weight w1 = new Main.Weight(1.0, Main.Unit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> {
            w1.add(null, Main.Unit.KILOGRAM);
        });
    }
}