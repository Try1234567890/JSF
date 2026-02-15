package me.tr.trformatter.strings.color.converter;

import java.util.Optional;

public class RGBConverter extends ColorConverter {

    @Override
    public Optional<int[]> toARGB(Object object) {
        if (!getValidator().validate(object)) {
            return Optional.empty();
        }
        int[] rgb = (int[]) object;

        int r = rgb[0];
        int g = rgb[1];
        int b = rgb[2];

        return Optional.of(new int[]{255, r, g, b});
    }

    @Override
    public Optional<int[]> fromARGB(int[] argb) {
        if (!getARGBValidator().validate(argb)) {
            return Optional.empty();
        }

        int r = argb[1];
        int g = argb[2];
        int b = argb[3];

        return Optional.of(new int[]{r, g, b});
    }
}
