package me.tr.trformatter.strings.color;

import me.tr.trformatter.strings.color.ansi.ANSI24Bit;
import me.tr.trformatter.strings.color.ansi.ANSI3Bit;
import me.tr.trformatter.strings.color.ansi.ANSI4Bit;
import me.tr.trformatter.strings.color.ansi.ANSI8Bit;
import me.tr.trformatter.strings.color.converter.ColorConverter;
import me.tr.trformatter.strings.color.converter.ConverterException;

public class Color {
    private final int[] rgb;
    private final int[] argb;
    private final String hexadecimal;
    private final int decimal;
    private final ANSI3Bit ansi3bit;
    private final ANSI4Bit ansi4bit;
    private final ANSI8Bit ansi8bit;
    private final ANSI24Bit ansi24bit;


    private Color(int[] argb, int[] rgb, String hexadecimal, int decimal) {
        this.argb = argb;
        this.rgb = rgb;
        this.hexadecimal = hexadecimal;
        this.decimal = decimal;
        this.ansi3bit = ANSI3Bit.getNearestFromRGB(rgb);
        this.ansi4bit = ANSI4Bit.getNearestFromRGB(rgb);
        this.ansi8bit = ANSI8Bit.getNearestFromRGB(rgb);
        this.ansi24bit = ANSI24Bit.ofRGB(rgb);
    }

    public static Color ofARGB(int[] argb) {
        String hex = ColorConverter.getARGBConverter().toHex(argb).orElseThrow(() -> new ConverterException("An error occurs while trying to convert ARGB color to Hex"));
        int[] rgb = ColorConverter.getARGBConverter().toRGB(argb).orElseThrow(() -> new ConverterException("An error occurs while trying to convert ARGB color to RGB"));
        int decimal = ColorConverter.getARGBConverter().toDecimal(argb).orElseThrow(() -> new ConverterException("An error occurs while trying to convert ARGB color to Decimal"));

        return new Color(argb, rgb, hex, decimal);
    }

    public static Color ofRGB(int[] rgb) {
        int[] argb = ColorConverter.getRGBConverter().toARGB(rgb).orElseThrow(() -> new ConverterException("An error occurs while trying to convert RGB color to ARGB"));
        String hex = ColorConverter.getRGBConverter().toHex(rgb).orElseThrow(() -> new ConverterException("An error occurs while trying to convert RGB color to Hex"));
        int decimal = ColorConverter.getRGBConverter().toDecimal(rgb).orElseThrow(() -> new ConverterException("An error occurs while trying to convert RGB color to Decimal"));

        return new Color(argb, rgb, hex, decimal);
    }

    public static Color ofDecimal(int decimal) {
        int[] argb = ColorConverter.getDecimalConverter().toARGB(decimal).orElseThrow(() -> new ConverterException("An error occurs while trying to convert Decimal color to ARGB"));
        int[] rgb = ColorConverter.getDecimalConverter().toRGB(decimal).orElseThrow(() -> new ConverterException("An error occurs while trying to convert Decimal color to RGB"));
        String hex = ColorConverter.getDecimalConverter().toHex(decimal).orElseThrow(() -> new ConverterException("An error occurs while trying to convert Decimal color to Hex"));

        return new Color(argb, rgb, hex, decimal);
    }


    public static Color ofHex(String hex) {
        int[] argb = ColorConverter.getHexConverter().toARGB(hex).orElseThrow(() -> new ConverterException("An error occurs while trying to convert Hex color to ARGB"));
        int[] rgb = ColorConverter.getHexConverter().toRGB(hex).orElseThrow(() -> new ConverterException("An error occurs while trying to convert Hex color to RGB"));
        int decimal = ColorConverter.getHexConverter().toDecimal(hex).orElseThrow(() -> new ConverterException("An error occurs while trying to convert Hex color to Decimal"));

        return new Color(argb, rgb, hex, decimal);
    }


    public int[] getRGB() {
        return rgb;
    }

    public int[] getARGB() {
        return argb;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    /**
     * Retrieve the hex color ({@code #AARRGGBB}) following
     * the provided pattern.
     * <p>
     * Channel tags are:
     * <ol>
     *     <li>Alpha: {@code AA}</li>
     *     <li>Red: {@code RR}</li>
     *     <li>Green: {@code GG}</li>
     *     <li>Blue: {@code BB}</li>
     * </ol>
     * So to retrieve hex as {@code #AARRGGBB} pattern is: {@code AARRGGBB}
     *
     * @param pattern The pattern to follow to format the hex color.
     * @return The formatted hex color.
     */
    public String getHex(String pattern) {
        String hex = getHexadecimal();

        return pattern
                .replace("AA", hex.substring(0, 2))
                .replace("RR", hex.substring(2, 4))
                .replace("GG", hex.substring(4, 6))
                .replace("BB", hex.substring(6, 8));
    }

    public int getDecimal() {
        return decimal;
    }

    public ANSI3Bit getANSI3Bit() {
        return ansi3bit;
    }

    public ANSI4Bit getANSI4Bit() {
        return ansi4bit;
    }

    public ANSI8Bit getANSI8Bit() {
        return ansi8bit;
    }

    public ANSI24Bit getANSI24Bit() {
        return ansi24bit;
    }
}
