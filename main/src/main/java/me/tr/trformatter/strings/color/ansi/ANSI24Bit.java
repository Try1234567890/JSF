package me.tr.trformatter.strings.color.ansi;

import me.tr.trformatter.strings.color.converter.ColorConverter;
import me.tr.trformatter.strings.color.validator.ColorValidator;

import java.util.Arrays;

public class ANSI24Bit implements ANSI {
    int r, g, b;

    protected ANSI24Bit(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static ANSI24Bit ofHex(String hex) {
        int[] rgb = ColorConverter.getHexConverter().toRGBOrThrown(hex);
        return ofRGB(rgb);
    }

    public static ANSI24Bit ofDecimal(int decimal) {
        int[] rgb = ColorConverter.getDecimalConverter().toRGBOrThrown(decimal);
        return ofRGB(rgb);
    }

    public static ANSI24Bit ofARGB(int[] argb) {
        int[] rgb = ColorConverter.getRGBConverter().toRGBOrThrown(argb);
        return ofRGB(rgb);
    }

    public static ANSI24Bit ofRGB(int[] rgb) {
        if (!ColorValidator.getRGBValidator().validate(rgb))
            throw new IllegalArgumentException("Invalid RGB (" + Arrays.toString(rgb) + ") Color");

        return new ANSI24Bit(rgb[0], rgb[1], rgb[2]);
    }

    @Override
    public String getName() {
        return "RGB[" + r + ", " + g + ", " + b + "]";
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public int getR() {
        return r;
    }

    @Override
    public int getG() {
        return g;
    }

    @Override
    public int getB() {
        return b;
    }

    @Override
    public String getTextEscapeSequence() {
        return "\u001B[38;2;" + getR() + ";" + getG() + ";" + getB() + "m";
    }

    @Override
    public String getBackgroundEscapeSequence() {
        return "\u001B[48;2;" + getR() + ";" + getG() + ";" + getB() + "m";
    }
}
