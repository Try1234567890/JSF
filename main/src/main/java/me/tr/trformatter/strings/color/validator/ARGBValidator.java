package me.tr.trformatter.strings.color.validator;

public class ARGBValidator extends ColorValidator {
    @Override
    public boolean validate(Object col) {
        if (!(col instanceof int[] color)) {
            return false;
        }

        if (color.length != 4)
            return false;

        int a = color[0];
        int r = color[1];
        int g = color[2];
        int b = color[3];

        return (a >= 0 && a <= 255)
                && (r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255);
    }
}
