package me.tr.trformatter.registries;

import me.tr.trformatter.strings.color.converter.*;
import me.tr.trformatter.strings.color.validator.*;

import java.util.Map;

public class ColorValidatorsRegistry extends Registry<Map.Entry<Class<? extends ColorValidator>, Class<? extends ColorConverter>>, ColorValidator> {
    private static ColorValidatorsRegistry instance;


    public static ColorValidatorsRegistry getInstance() {
        if (instance == null) {
            instance = new ColorValidatorsRegistry();
            instance.register(Map.entry(ARGBValidator.class, ARGBConverter.class), new ARGBValidator());
            instance.register(Map.entry(RGBValidator.class, RGBConverter.class), new RGBValidator());
            instance.register(Map.entry(HexValidator.class, HexConverter.class), new HexValidator());
            instance.register(Map.entry(DecimalValidator.class, DecimalConverter.class), new DecimalValidator());
        }
        return instance;
    }

    public ColorValidator retrieveFromValidator(Class<? extends ColorValidator> key) {
        for (Map.Entry<Map.Entry<Class<? extends ColorValidator>, Class<? extends ColorConverter>>, ColorValidator>
                entry : getRegistry().entrySet()) {

            if (entry.getKey().getKey().equals(key)) {
                return entry.getValue();
            }

        }

        return null;
    }

    public ColorValidator retrieveFromConverter(Class<? extends ColorConverter> key) {
        for (Map.Entry<Map.Entry<Class<? extends ColorValidator>, Class<? extends ColorConverter>>, ColorValidator>
                entry : getRegistry().entrySet()) {

            if (entry.getKey().getValue().equals(key)) {
                return entry.getValue();
            }

        }

        return null;
    }
}
