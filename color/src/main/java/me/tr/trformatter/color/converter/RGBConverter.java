package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.ARGBValidator;

import java.util.Arrays;

public class RGBConverter implements ColorConverter<int[]> {
    private RGBConverter() {}

    private record Holder() {
        private static final RGBConverter INSTANCE = new RGBConverter();
    }

    public static RGBConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public int[] toARGB(int[] rgb) throws ColorConversionException {
        try {
            return ARGBConverter.ensureAlphaChannel(rgb);
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(rgb) + " conversion failed.", t);
        }
    }

    @Override
    public int[] fromARGB(int[] color) throws ColorConversionException {
        try {
            int[] argb = ARGBConverter.ensureAlphaChannel(color);
            return new int[]{argb[1], argb[2], argb[3]};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }
}
