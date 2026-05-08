import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testSubtractionSameUnit() {
        Main.Quantity<Main.LengthUnit> q1 = new Main.Quantity<>(10, Main.LengthUnit.FEET);
        Main.Quantity<Main.LengthUnit> q2 = new Main.Quantity<>(5, Main.LengthUnit.FEET);
        assertEquals(new Main.Quantity<>(5, Main.LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtractionCrossUnit() {
        Main.Quantity<Main.LengthUnit> q1 = new Main.Quantity<>(10, Main.LengthUnit.FEET);
        Main.Quantity<Main.LengthUnit> q2 = new Main.Quantity<>(6, Main.LengthUnit.INCHES);
        assertEquals(new Main.Quantity<>(9.5, Main.LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtractionNegative() {
        Main.Quantity<Main.WeightUnit> q1 = new Main.Quantity<>(2, Main.WeightUnit.KILOGRAM);
        Main.Quantity<Main.WeightUnit> q2 = new Main.Quantity<>(5, Main.WeightUnit.KILOGRAM);
        assertEquals(new Main.Quantity<>(-3, Main.WeightUnit.KILOGRAM), q1.subtract(q2));
    }

    @Test
    void testSubtractionZero() {
        Main.Quantity<Main.VolumeUnit> q1 = new Main.Quantity<>(1, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> q2 = new Main.Quantity<>(1000, Main.VolumeUnit.MILLILITRE);
        assertEquals(new Main.Quantity<>(0, Main.VolumeUnit.LITRE), q1.subtract(q2));
    }

    @Test
    void testDivisionSameUnit() {
        Main.Quantity<Main.LengthUnit> q1 = new Main.Quantity<>(10, Main.LengthUnit.FEET);
        Main.Quantity<Main.LengthUnit> q2 = new Main.Quantity<>(2, Main.LengthUnit.FEET);
        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testDivisionCrossUnit() {
        Main.Quantity<Main.LengthUnit> q1 = new Main.Quantity<>(24, Main.LengthUnit.INCHES);
        Main.Quantity<Main.LengthUnit> q2 = new Main.Quantity<>(2, Main.LengthUnit.FEET);
        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void testDivisionLessThanOne() {
        Main.Quantity<Main.VolumeUnit> q1 = new Main.Quantity<>(5, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> q2 = new Main.Quantity<>(10, Main.VolumeUnit.LITRE);
        assertEquals(0.5, q1.divide(q2));
    }

    @Test
    void testDivisionByZero() {
        Main.Quantity<Main.WeightUnit> q1 = new Main.Quantity<>(10, Main.WeightUnit.KILOGRAM);
        Main.Quantity<Main.WeightUnit> q2 = new Main.Quantity<>(0, Main.WeightUnit.KILOGRAM);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testCrossCategorySubtraction() {
        Main.Quantity<Main.LengthUnit> q1 = new Main.Quantity<>(10, Main.LengthUnit.FEET);
        Main.Quantity<Main.WeightUnit> q2 = new Main.Quantity<>(5, Main.WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract((Main.Quantity) q2));
    }

    @Test
    void testCrossCategoryDivision() {
        Main.Quantity<Main.LengthUnit> q1 = new Main.Quantity<>(10, Main.LengthUnit.FEET);
        Main.Quantity<Main.WeightUnit> q2 = new Main.Quantity<>(5, Main.WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> q1.divide((Main.Quantity) q2));
    }
}