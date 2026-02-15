package me.tr.trformatter.strings.color.converter;

import java.util.Optional;

public class DecimalConverter extends ColorConverter {


    @Override
    public Optional<int[]> toARGB(Object object) {
        if (!getValidator().validate(object)) {
            return Optional.empty();
        }
        int decimal = (int) object;

        int a = (decimal >> 24) & 0xFF;
        int r = (decimal >> 16) & 0xFF;
        int g = (decimal >> 8) & 0xFF;
        int b = decimal & 0xFF;

        return Optional.of(new int[]{a, r, g, b});
    }

    @Override
    public Optional<Integer> fromARGB(int[] argb) {
        if (!getARGBValidator().validate(argb)) {
            return Optional.empty();
        }

        int a = (argb[0] & 0xFF) << 24;
        int r = (argb[1] & 0xFF) << 16;
        int g = (argb[2] & 0xFF) << 8;
        int b = argb[3] & 0xFF;

        return Optional.of(a | r | g | b);
    }
}
