package trformatter;


import me.tr.trformatter.strings.color.converter.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ColorConverterTest {

    @Test
    public void testHexConverter() {
        HexConverter converter = ColorConverter.getHexConverter();
        String hex = "#FF5733";

        int[] argb = converter.toARGBOrThrown(hex);
        assertArrayEquals(new int[]{255, 255, 87, 51}, argb);

        Optional<String> backToHex = converter.fromARGB(new int[]{255, 255, 87, 51});
        assertTrue(backToHex.isPresent());
        assertEquals("FFFF5733", backToHex.get());
    }

    @Test
    public void testDecimalConverter() {
        DecimalConverter converter = ColorConverter.getDecimalConverter();

        int blackDecimal = -16777216;
        int[] argb = converter.toARGBOrThrown(blackDecimal);
        assertArrayEquals(new int[]{255, 0, 0, 0}, argb);

        int decimal = converter.fromARGB(new int[]{255, 255, 255, 255}).get();
        assertEquals(-1, decimal);
    }

    @Test
    public void testRGBConverter() {
        RGBConverter converter = ColorConverter.getRGBConverter();
        int[] rgbInput = new int[]{255, 87, 51};

        int[] argb = converter.toARGBOrThrown(rgbInput);
        assertArrayEquals(new int[]{255, 255, 87, 51}, argb);

        int[] backToRgb = converter.fromARGB(new int[]{128, 255, 87, 51}).get();
        assertArrayEquals(new int[]{255, 87, 51}, backToRgb);
    }

    @Test
    public void testARGBConverter() {
        ARGBConverter converter = ColorConverter.getARGBConverter();
        int[] argbInput = new int[]{255, 10, 20, 30};

        assertArrayEquals(argbInput, converter.toARGBOrThrown(argbInput));
        assertArrayEquals(argbInput, converter.fromARGB(argbInput).get());
    }

    @Test
    public void testCrossConversion() {
        HexConverter hexConv = ColorConverter.getHexConverter();

        Integer decimal = hexConv.toDecimalOrThrown("#FFFFFF");
        assertEquals(-1, decimal);

        int[] rgb = hexConv.toRGBOrThrown("#FF0000");
        assertArrayEquals(new int[]{255, 0, 0}, rgb);
    }

    @Test
    public void testInvalidInputThrowsException() {
        HexConverter converter = ColorConverter.getHexConverter();

        assertThrows(ConverterException.class, () -> converter.toARGBOrThrown("NON_SONO_UN_COLORE"));
    }

    @Test
    public void testHexWithAlpha() {
        HexConverter converter = ColorConverter.getHexConverter();
        String hexWithAlpha = "80FF5733";

        int[] argb = converter.toARGBOrThrown(hexWithAlpha);
        assertEquals(128, argb[0]);
        assertEquals(255, argb[1]);
    }
}
