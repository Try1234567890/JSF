package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.IntegerHexValidator;

import java.util.Arrays;

public class IntegerHexConverter implements ColorConverter<Integer> {
    private IntegerHexConverter() {
    }

    private record Holder() {
        private static final IntegerHexConverter INSTANCE = new IntegerHexConverter();
    }

    public static IntegerHexConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public int[] toARGB(Integer color) throws ColorConversionException {
        try {
        String asHex = Integer.toHexString(color);
        return StringHexConverter.getInstance().toARGB(asHex);
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + color + " conversion failed.", t);
        }
    }

    @Override
    public Integer fromARGB(int[] argb) throws ColorConversionException {
        try {
        String asHex = StringHexConverter.getInstance().fromARGB(argb);
        return Integer.parseInt(asHex, 16);
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(argb) + " conversion failed.", t);
        }
    }

    @Override
    public Integer fromIntegerHex(int hex) throws ColorConversionException {
        return IntegerHexValidator.getInstance().validate(hex);
    }

    @Override
    public int toIntegerHex(Integer color) throws ColorConversionException {
        return IntegerHexValidator.getInstance().validate(color);
    }
}
