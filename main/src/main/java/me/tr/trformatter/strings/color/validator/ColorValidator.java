package me.tr.trformatter.strings.color.validator;

import me.tr.trformatter.registries.ColorConvertersRegistry;
import me.tr.trformatter.registries.ColorValidatorsRegistry;
import me.tr.trformatter.strings.color.converter.*;

public abstract class ColorValidator {

    public abstract boolean validate(Object color);

    public ColorConverter getConverter() {
        return ColorConvertersRegistry.getInstance().retrieveFromValidator(getClass());
    }

    public ARGBConverter getARGBConverter() {
        return ColorConverter.getARGBConverter();
    }

    public RGBConverter getRGBConverter() {
        return ColorConverter.getRGBConverter();
    }

    public HexConverter getHexConverter() {
        return ColorConverter.getHexConverter();
    }

    public DecimalConverter getDecimalConverter() {
        return ColorConverter.getDecimalConverter();
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColorValidator> T getValidator(Class<T> tClass) {
        return (T) ColorValidatorsRegistry.getInstance().retrieveFromValidator(tClass);
    }

    public ColorValidator getValidator() {
        return getValidator(getClass());
    }

    public static ARGBValidator getARGBValidator() {
        return getValidator(ARGBValidator.class);
    }

    public static RGBValidator getRGBValidator() {
        return getValidator(RGBValidator.class);
    }

    public static HexValidator getHexValidator() {
        return getValidator(HexValidator.class);
    }

    public static DecimalValidator getDecimalValidator() {
        return getValidator(DecimalValidator.class);
    }
}
