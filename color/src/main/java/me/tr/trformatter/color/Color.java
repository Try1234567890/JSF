package me.tr.trformatter.color;

import me.tr.trformatter.color.converter.*;
import me.tr.trformatter.color.validator.ARGBValidator;
import me.tr.utilities.validators.Preconditions;

public class Color {
    private final int a, r, g, b;
    private final int[] argb;

    public Color(int[] argb) {
        this.argb = ARGBConverter.ensureAlphaChannel(argb); // Validate the channels too.
        this.a = argb[0];
        this.r = argb[1];
        this.g = argb[2];
        this.b = argb[3];
    }

    public static Color ofARGB(int[] argb) {
         // Validation are made in the constructor
        return new Color(argb);
    }

    public static Color ofARGB(int a, int r, int g, int b) {
         // Validation are made in the constructor
        return ofARGB(new int[]{a, r, g, b});
    }

    public static Color ofRGB(int r, int g, int b) {
         // Validation are made in the constructor
        return ofARGB(new int[]{255, r, g, b});
    }

    public static Color ofDecimalRGB(int decimal) {
        return ofARGB(DecimalConverter.getInstance().toARGB(decimal));
    }

    public static Color ofHex(String hex) {
        return ofARGB(StringHexConverter.getInstance().toARGB(hex));
    }

    public static Color ofIntegerHex(int hex) {
        return ofARGB(IntegerHexConverter.getInstance().toARGB(hex));
    }

    public static Color ofHSB(double[] hsb) {
        return ofARGB(HSBConverter.getInstance().toARGB(hsb));
    }

    public static Color ofHSL(double[] hsl) {
        return ofARGB(HSLConverter.getInstance().toARGB(hsl));
    }

    public static Color ofAWTColor(java.awt.Color color) {
        Preconditions.parameterNotNull(color, "color");
        // Validation are made in the constructor
        return ofARGB(new int[]{color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue()});
    }

    public int getA() {
        return a;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int[] toARGB() {
        return argb;
    }

    public int[] toRGB() {
        return RGBConverter.getInstance().fromARGB(argb);
    }

    public int toDecimalRGB() {
        return DecimalConverter.getInstance().fromARGB(argb);
    }

    public double[] toHSL() {
        return HSLConverter.getInstance().fromARGB(argb);
    }

    public double[] toHSB() {
        return HSBConverter.getInstance().fromARGB(argb);
    }

    public int toIntegerHex() {
        return IntegerHexConverter.getInstance().fromARGB(argb);
    }

    public String toHex() {
        return StringHexConverter.getInstance().fromARGB(argb);
    }
}
