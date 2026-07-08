package me.tr.trformatter.color.ansi;

import me.tr.trformatter.color.converter.*;
import me.tr.trformatter.color.exceptions.InvalidColorException;

public final class ANSI24Bit implements ANSI {
    private final int r, g, b;

    private ANSI24Bit(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static ANSI24Bit ofHex(int hex) throws InvalidColorException {
        int[] rgb = IntegerHexConverter.getInstance().toARGB(hex);
        return ofARGB(rgb);
    }

    public static ANSI24Bit ofHex(String hex) throws InvalidColorException {
        int[] rgb = StringHexConverter.getInstance().toARGB(hex);
        return ofARGB(rgb);
    }

    public static ANSI24Bit ofHSL(double[] hsl) throws InvalidColorException {
        int[] rgb = HSLConverter.getInstance().toARGB(hsl);
        return ofARGB(rgb);
    }

    public static ANSI24Bit ofHSB(double[] hsb) throws InvalidColorException {
        int[] rgb = HSBConverter.getInstance().toARGB(hsb);
        return ofARGB(rgb);
    }

    public static ANSI24Bit ofDecimal(int decimal) throws InvalidColorException {
        int[] rgb = DecimalConverter.getInstance().toARGB(decimal);
        return ofARGB(rgb);
    }

    public static ANSI24Bit ofARGB(int[] argb) throws InvalidColorException {
        int[] rgb = ARGBConverter.ensureAlphaChannel(argb); // Implicit validation.
        return new ANSI24Bit(rgb[1], rgb[2], rgb[3]);
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
