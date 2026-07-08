package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.ARGBValidator;

import java.util.Arrays;

public class ARGBConverter implements ColorConverter<int[]> {
    private ARGBConverter() {}

    private record Holder() {
        private static final ARGBConverter INSTANCE = new ARGBConverter();
    }

    public static ARGBConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public int[] toARGB(int[] color) throws ColorConversionException {
        try {
            ARGBValidator.getInstance().validate(color);
            return color;
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    @Override
    public int[] fromARGB(int[] argb) throws ColorConversionException {
        try {
            ARGBValidator.getInstance().validate(argb);
            return argb;
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(argb) + " conversion failed.", t);
        }
    }

    public static int[] ensureAlphaChannel(int[] argbOrRGB) {
        ARGBValidator.getInstance().validate(argbOrRGB);
        if (argbOrRGB.length == 4) return argbOrRGB;
        return new int[] { 255, argbOrRGB[0], argbOrRGB[1], argbOrRGB[2] };
    }

    public static double[] normalize(int[] color) {
        int[] argb = ensureAlphaChannel(color);
        return new double[] { argb[0] / 255D, argb[1] / 255D, argb[2] / 255D, argb[3] / 255D };
    }
}
