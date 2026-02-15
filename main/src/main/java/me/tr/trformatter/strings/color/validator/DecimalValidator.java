package me.tr.trformatter.strings.color.validator;

public class DecimalValidator extends ColorValidator {
    @Override
    public boolean validate(Object col) {
        if (!(col instanceof Integer color)) return false;

        // Allows 0x00000000 through 0xFFFFFFFF
        return Integer.compareUnsigned(color, 0) >= 0 &&
                Integer.compareUnsigned(color, 0xFFFFFFFF) <= 0;
    }
}
