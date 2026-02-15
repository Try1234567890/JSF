package me.tr.trformatter.strings.color.converter;

import java.util.Optional;

public class ARGBConverter extends ColorConverter {


    @Override
    public Optional<int[]> toARGB(Object object) {
        if (!getValidator().validate(object)) {
            return Optional.empty();
        }
        return Optional.ofNullable((int[]) object);
    }

    @Override
    public Optional<int[]> fromARGB(int[] argb) {
        if (!getValidator().validate(argb)) {
            return Optional.empty();
        }
        return Optional.of(argb);
    }
}
