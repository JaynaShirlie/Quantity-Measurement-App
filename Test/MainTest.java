import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testLengthEquality() {
        assertTrue(new Quantity<>(1.0, LengthUnit.FOOT).equals(new Quantity<>(12.0, LengthUnit.INCH)));
    }

    @Test
    void testWeightEquality() {
        assertTrue(new Quantity<>(1.0, WeightUnit.KILOGRAM).equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testVolumeEquality() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testConversion() {
        assertEquals(12.0, new Quantity<>(1.0, LengthUnit.FOOT).convertTo(LengthUnit.INCH), 0.01);
    }

    @Test
    void testAddition() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FOOT)
                .add(new Quantity<>(12.0, LengthUnit.INCH));
        assertEquals(2.0, q.getValue());
    }

    @Test
    void testSubtraction() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FOOT)
                .subtract(new Quantity<>(6.0, LengthUnit.INCH));
        assertEquals(9.5, q.getValue());
    }

    @Test
    void testSubtractionNegative() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FOOT)
                .subtract(new Quantity<>(10.0, LengthUnit.FOOT));
        assertEquals(-5.0, q.getValue());
    }

    @Test
    void testSubtractionZero() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FOOT)
                .subtract(new Quantity<>(120.0, LengthUnit.INCH));
        assertEquals(0.0, q.getValue());
    }

    @Test
    void testDivisionSameUnit() {
        double result = new Quantity<>(10.0, LengthUnit.FOOT)
                .divide(new Quantity<>(2.0, LengthUnit.FOOT));
        assertEquals(5.0, result);
    }

    @Test
    void testDivisionCrossUnit() {
        double result = new Quantity<>(24.0, LengthUnit.INCH)
                .divide(new Quantity<>(2.0, LengthUnit.FOOT));
        assertEquals(1.0, result);
    }

    @Test
    void testDivisionLessThanOne() {
        double result = new Quantity<>(5.0, LengthUnit.FOOT)
                .divide(new Quantity<>(10.0, LengthUnit.FOOT));
        assertEquals(0.5, result);
    }

    @Test
    void testDivisionGreaterThanOne() {
        double result = new Quantity<>(10.0, LengthUnit.FOOT)
                .divide(new Quantity<>(5.0, LengthUnit.FOOT));
        assertEquals(2.0, result);
    }

    @Test
    void testDivisionEqualOne() {
        double result = new Quantity<>(10.0, LengthUnit.FOOT)
                .divide(new Quantity<>(10.0, LengthUnit.FOOT));
        assertEquals(1.0, result);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () ->
                new Quantity<>(10.0, LengthUnit.FOOT)
                        .divide(new Quantity<>(0.0, LengthUnit.FOOT)));
    }

    @Test
    void testCrossCategoryAddition() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FOOT)
                        .add((Quantity) new Quantity<>(5.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testCrossCategorySubtraction() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FOOT)
                        .subtract((Quantity) new Quantity<>(5.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testCrossCategoryDivision() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FOOT)
                        .divide((Quantity) new Quantity<>(5.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testNullOperand() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FOOT).add(null));
    }

    @Test
    void testNullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FOOT)
                        .add(new Quantity<>(5.0, LengthUnit.FOOT), null));
    }

    @Test
    void testImmutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FOOT);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FOOT);
        q1.add(q2);
        assertEquals(10.0, q1.getValue());
    }

    @Test
    void testChainedOperations() {
        double result = new Quantity<>(10.0, LengthUnit.FOOT)
                .add(new Quantity<>(2.0, LengthUnit.FOOT))
                .subtract(new Quantity<>(1.0, LengthUnit.FOOT))
                .divide(new Quantity<>(2.0, LengthUnit.FOOT));
        assertEquals(5.5, result);
    }

    @Test
    void testLargeValues() {
        double result = new Quantity<>(1e6, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM));
        assertEquals(1e6, result);
    }

    @Test
    void testSmallValues() {
        double result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM));
        assertEquals(1e-6, result);
    }
}