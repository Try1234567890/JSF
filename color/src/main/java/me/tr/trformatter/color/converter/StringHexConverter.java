package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.ARGBValidator;
import me.tr.trformatter.color.validator.StringHexValidator;

import java.util.Arrays;

public class StringHexConverter implements ColorConverter<String> {
    private StringHexConverter() {
    }

    private record Holder() {
        private static final StringHexConverter INSTANCE = new StringHexConverter();
    }

    public static StringHexConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public int[] toARGB(String color) throws ColorConversionException {
        try {
            // Implicit validation inside ensureAlphaChannel();
            String hex = ensureAlphaChannel(color);

            int a = Integer.parseInt(hex.substring(0, 2), 16);
            int r = Integer.parseInt(hex.substring(2, 4), 16);
            int g = Integer.parseInt(hex.substring(4, 6), 16);
            int b = Integer.parseInt(hex.substring(6, 8), 16);

            return new int[]{a, r, g, b};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + color + " conversion failed.", t);
        }
    }

    @Override
    public String fromARGB(int[] color) throws ColorConversionException {
        try {
            int[] argb = ARGBConverter.ensureAlphaChannel(color);

            return String.format("%02X%02X%02X%02X", argb[0], argb[1], argb[2], argb[3]);
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    @Override
    public String fromHex(String hex) throws ColorConversionException {
        return StringHexValidator.getInstance().validate(hex);
    }

    @Override
    public String toHex(String color) throws ColorConversionException {
        return StringHexValidator.getInstance().validate(color);
    }

    public static String ensureAlphaChannel(String color) {
        String hex = StringHexValidator.getInstance().validate(color);
        if (hex.length() == 6) return "FF" + hex;
        return hex;
    }
}
