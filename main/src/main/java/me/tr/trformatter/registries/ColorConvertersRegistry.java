package me.tr.trformatter.registries;

import me.tr.trformatter.strings.color.converter.*;
import me.tr.trformatter.strings.color.validator.*;

import java.util.Map;

public class ColorConvertersRegistry extends Registry<Map.Entry<Class<? extends ColorValidator>, Class<? extends ColorConverter>>, ColorConverter> {
    private static ColorConvertersRegistry instance;


    public static ColorConvertersRegistry getInstance() {
        if (instance == null) {
            instance = new ColorConvertersRegistry();
            instance.register(Map.entry(ARGBValidator.class, ARGBConverter.class), new ARGBConverter());
            instance.register(Map.entry(RGBValidator.class, RGBConverter.class), new RGBConverter());
            instance.register(Map.entry(HexValidator.class, HexConverter.class), new HexConverter());
            instance.register(Map.entry(DecimalValidator.class, DecimalConverter.class), new DecimalConverter());
        }
        return instance;
    }

    public ColorConverter retrieveFromValidator(Class<? extends ColorValidator> key) {
        for (Map.Entry<Map.Entry<Class<? extends ColorValidator>, Class<? extends ColorConverter>>, ColorConverter>
                entry : getRegistry().entrySet()) {

            if (entry.getKey().getKey().equals(key)) {
                return entry.getValue();
            }

        }

        return null;
    }

    public ColorConverter retrieveFromConverter(Class<? extends ColorConverter> key) {
        for (Map.Entry<Map.Entry<Class<? extends ColorValidator>, Class<? extends ColorConverter>>, ColorConverter>
                entry : getRegistry().entrySet()) {

            if (entry.getKey().getValue().equals(key)) {
                return entry.getValue();
            }

        }

        return null;
    }
}
