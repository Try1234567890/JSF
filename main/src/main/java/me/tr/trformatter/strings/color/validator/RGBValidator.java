package me.tr.trformatter.strings.color.validator;

public class RGBValidator extends ColorValidator {
    @Override
    public boolean validate(Object col) {
        if (!(col instanceof int[] color)) {
            return false;
        }

        if (color.length != 3)
            return false;

        int r = color[0];
        int g = color[1];
        int b = color[2];

        return (r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255);
    }
}
