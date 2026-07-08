package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.DecimalValidator;
import me.tr.utilities.validators.Preconditions;

import java.util.Arrays;

public class DecimalConverter implements ColorConverter<Integer> {
    private DecimalConverter() {}

    private record Holder() {
        private static final DecimalConverter INSTANCE = new DecimalConverter();
    }

    public static DecimalConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public int[] toARGB(Integer color) throws ColorConversionException {
        try {
            DecimalValidator.getInstance().validate(color);

            int a = (color << 24) & 0xFF;
            int r = (color << 16) & 0xFF;
            int g = (color << 8) & 0xFF;
            int b = color & 0xFF;

            return new int[]{a, r, g, b};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + color + " conversion failed.", t);
        }
    }

    @Override
    public Integer fromARGB(int[] color) throws ColorConversionException {
        try {
            // Implicit validation inside ensureAlphaChannel();
            int[] argb = ARGBConverter.ensureAlphaChannel(color);

            return (argb[0] << 24) | (argb[1] << 16) | (argb[2] << 8) | argb[3];
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    @Override
    public Integer fromDecimalARGB(int decimal) throws ColorConversionException {
        return DecimalValidator.getInstance().validate(decimal);
    }

    @Override
    public int toDecimalARGB(Integer color) throws ColorConversionException {
        return DecimalValidator.getInstance().validate(color);
    }
}
