import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void volumeLiterEqualsMilliliters() {
        Main.Quantity<Main.VolumeUnit> l = new Main.Quantity<>(1.0, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> ml = new Main.Quantity<>(1000.0, Main.VolumeUnit.MILLILITRE);
        assertTrue(l.equals(ml));
    }

    @Test
    void volumeLiterEqualsGallon() {
        Main.Quantity<Main.VolumeUnit> l = new Main.Quantity<>(3.78541, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> g = new Main.Quantity<>(1.0, Main.VolumeUnit.GALLON);
        assertTrue(l.equals(g));
    }

    @Test
    void convertVolumeLitersToMilliliters() {
        Main.Quantity<Main.VolumeUnit> l = new Main.Quantity<>(1.0, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> result = l.convertTo(Main.VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue());
    }

    @Test
    void convertVolumeGallonToLiter() {
        Main.Quantity<Main.VolumeUnit> g = new Main.Quantity<>(1.0, Main.VolumeUnit.GALLON);
        Main.Quantity<Main.VolumeUnit> result = g.convertTo(Main.VolumeUnit.LITRE);
        assertEquals(3.79, result.getValue());
    }

    @Test
    void addVolumeLitersAndMilliliters() {
        Main.Quantity<Main.VolumeUnit> l = new Main.Quantity<>(1.0, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> ml = new Main.Quantity<>(1000.0, Main.VolumeUnit.MILLILITRE);
        Main.Quantity<Main.VolumeUnit> result = l.add(ml, Main.VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void addVolumeLitersAndGallon() {
        Main.Quantity<Main.VolumeUnit> l = new Main.Quantity<>(1.0, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.VolumeUnit> g = new Main.Quantity<>(1.0, Main.VolumeUnit.GALLON);
        Main.Quantity<Main.VolumeUnit> result = l.add(g, Main.VolumeUnit.LITRE);
        assertEquals(4.79, result.getValue());
    }

    @Test
    void preventVolumeLengthComparison() {
        Main.Quantity<Main.VolumeUnit> v = new Main.Quantity<>(1.0, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.LengthUnit> l = new Main.Quantity<>(1.0, Main.LengthUnit.FEET);
        assertFalse(v.equals(l));
    }

    @Test
    void preventVolumeWeightComparison() {
        Main.Quantity<Main.VolumeUnit> v = new Main.Quantity<>(1.0, Main.VolumeUnit.LITRE);
        Main.Quantity<Main.WeightUnit> w = new Main.Quantity<>(1.0, Main.WeightUnit.KILOGRAM);
        assertFalse(v.equals(w));
    }
}