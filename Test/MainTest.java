import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void lengthFeetEqualsInches() {
        Main.Quantity<Main.LengthUnit> feet = new Main.Quantity<>(1.0, Main.LengthUnit.FEET);
        Main.Quantity<Main.LengthUnit> inches = new Main.Quantity<>(12.0, Main.LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    void lengthYardsEqualsFeet() {
        Main.Quantity<Main.LengthUnit> yards = new Main.Quantity<>(1.0, Main.LengthUnit.YARDS);
        Main.Quantity<Main.LengthUnit> feet = new Main.Quantity<>(3.0, Main.LengthUnit.FEET);
        assertTrue(yards.equals(feet));
    }

    @Test
    void weightKilogramEqualsGrams() {
        Main.Quantity<Main.WeightUnit> kg = new Main.Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Main.Quantity<Main.WeightUnit> grams = new Main.Quantity<>(1000.0, Main.WeightUnit.GRAM);
        assertTrue(kg.equals(grams));
    }

    @Test
    void weightPoundEqualsGrams() {
        Main.Quantity<Main.WeightUnit> pound = new Main.Quantity<>(1.0, Main.WeightUnit.POUND);
        Main.Quantity<Main.WeightUnit> grams = new Main.Quantity<>(453.592, Main.WeightUnit.GRAM);
        assertTrue(pound.equals(grams));
    }

    @Test
    void convertLengthFeetToInches() {
        Main.Quantity<Main.LengthUnit> feet = new Main.Quantity<>(1.0, Main.LengthUnit.FEET);
        Main.Quantity<Main.LengthUnit> result = feet.convertTo(Main.LengthUnit.INCHES);
        assertEquals(12.0, result.getValue());
    }

    @Test
    void convertWeightKilogramsToGrams() {
        Main.Quantity<Main.WeightUnit> kg = new Main.Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Main.Quantity<Main.WeightUnit> result = kg.convertTo(Main.WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue());
    }

    @Test
    void addLengthFeetAndInches() {
        Main.Quantity<Main.LengthUnit> feet = new Main.Quantity<>(1.0, Main.LengthUnit.FEET);
        Main.Quantity<Main.LengthUnit> inches = new Main.Quantity<>(12.0, Main.LengthUnit.INCHES);
        Main.Quantity<Main.LengthUnit> result = feet.add(inches, Main.LengthUnit.FEET);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void addWeightKilogramsAndGrams() {
        Main.Quantity<Main.WeightUnit> kg = new Main.Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        Main.Quantity<Main.WeightUnit> grams = new Main.Quantity<>(1000.0, Main.WeightUnit.GRAM);
        Main.Quantity<Main.WeightUnit> result = kg.add(grams, Main.WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void preventCrossTypeComparison() {
        Main.Quantity<Main.LengthUnit> length = new Main.Quantity<>(1.0, Main.LengthUnit.FEET);
        Main.Quantity<Main.WeightUnit> weight = new Main.Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight));
    }

    @Test
    void preventCrossTypeAddition() {
        Main.Quantity<Main.LengthUnit> length = new Main.Quantity<>(1.0, Main.LengthUnit.FEET);
        Main.Quantity<Main.WeightUnit> weight = new Main.Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> {
            length.add((Main.Quantity) weight);
        });
    }

    @Test
    void constructorRejectsNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Main.Quantity<>(1.0, null);
        });
    }

    @Test
    void constructorRejectsInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Main.Quantity<>(Double.NaN, Main.LengthUnit.FEET);
        });
    }
}