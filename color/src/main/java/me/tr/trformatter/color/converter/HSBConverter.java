package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.HSB_HSLValidator;

public class HSBConverter extends HSLConverter {
    private HSBConverter() {}

    private record Holder() {
        private static final HSBConverter INSTANCE = new HSBConverter();
    }

    public static HSBConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected double getSaturation(double c, double v, double l) {
        if (v == 0.0) return 0;
        return c / v;
    }

    @Override
    public double[] fromHSB(double[] hsb) throws ColorConversionException {
        return HSB_HSLValidator.getInstance().validate(hsb);
    }

    @Override
    public double[] toHSB(double[] color) throws ColorConversionException {
        return HSB_HSLValidator.getInstance().validate(color);
    }
}