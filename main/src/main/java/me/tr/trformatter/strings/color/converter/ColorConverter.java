package me.tr.trformatter.strings.color.converter;

import me.tr.trformatter.registries.ColorConvertersRegistry;
import me.tr.trformatter.registries.ColorValidatorsRegistry;
import me.tr.trformatter.strings.color.validator.*;

import java.util.Optional;

public abstract class ColorConverter {

    public abstract Optional<int[]> toARGB(Object object);

    public abstract Optional<?> fromARGB(int[] argb);

    public Optional<int[]> toRGB(Object object) {
        int[] argb = toARGBOrThrown(object);
        return getRGBConverter().fromARGB(argb);
    }

    public Optional<Integer> toDecimal(Object object) {
        int[] argb = toARGBOrThrown(object);
        return getDecimalConverter().fromARGB(argb);
    }

    public Optional<String> toHex(Object object) {
        int[] argb = toARGBOrThrown(object);
        return getHexConverter().fromARGB(argb);
    }

    public int[] toARGBOrNull(Object object) {
        return toARGB(object).orElse(null);
    }

    public int[] toARGBOrThrown(Object object) {
        return toARGB(object).orElseThrow(() -> new ConverterException("An error occurs while trying to convert color " + object + " to ARGB"));
    }

    public int[] toARGBOr(Object object, int[] def) {
        return toARGB(object).orElse(def);
    }

    public int[] toRGBOrNull(Object object) {
        return toRGB(object).orElse(null);
    }

    public int[] toRGBOrThrown(Object object) {
        return toRGB(object).orElseThrow(() -> new ConverterException("An error occurs while trying to convert color " + object + " to ARGB"));
    }

    public int[] toRGBOr(Object object, int[] def) {
        return toRGB(object).orElse(def);
    }

    public Integer toDecimalOrNull(Object object) {
        return toDecimal(object).orElse(null);
    }

    public Integer toDecimalOrThrown(Object object) {
        return toDecimal(object).orElseThrow(() -> new ConverterException("An error occurs while trying to convert color " + object + " to ARGB"));
    }

    public Integer toDecimalOr(Object object, Integer def) {
        return toDecimal(object).orElse(def);
    }

    public String toHexOrNull(Object object) {
        return toHex(object).orElse(null);
    }

    public String toHexOrThrown(Object object) {
        return toHex(object).orElseThrow(() -> new ConverterException("An error occurs while trying to convert color " + object + " to ARGB"));
    }

    public String toHexOr(Object object, String def) {
        return toHex(object).orElse(def);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColorConverter> T getConverter(Class<T> tClass) {
        return (T) ColorConvertersRegistry.getInstance().retrieveFromConverter(tClass);
    }

    public ColorConverter getConverter() {
        return getConverter(getClass());
    }

    public static ARGBConverter getARGBConverter() {
        return getConverter(ARGBConverter.class);
    }

    public static RGBConverter getRGBConverter() {
        return getConverter(RGBConverter.class);
    }

    public static HexConverter getHexConverter() {
        return getConverter(HexConverter.class);
    }

    public static DecimalConverter getDecimalConverter() {
        return getConverter(DecimalConverter.class);
    }

    public static <T extends ColorConverter> ColorValidator getValidator(Class<T> tClass) {
        return ColorValidatorsRegistry.getInstance().retrieveFromConverter(tClass);
    }

    public ColorValidator getValidator() {
        return getValidator(getClass());
    }

    public static ARGBValidator getARGBValidator() {
        return ColorValidator.getARGBValidator();
    }

    public static RGBValidator getRGBValidator() {
        return ColorValidator.getRGBValidator();
    }

    public static HexValidator getHexValidator() {
        return ColorValidator.getHexValidator();
    }

    public static DecimalValidator getDecimalValidator() {
        return ColorValidator.getDecimalValidator();
    }
}
