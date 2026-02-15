package me.tr.trformatter.strings.color.validator;

import java.util.Set;

public class HexValidator extends ColorValidator {
    @Override
    public boolean validate(Object col) {
        if (!(col instanceof String colorStr)) {
            return false;
        }

        String color = getHex(colorStr);

        int length = color.length();

        if (length != 6 && length != 8)
            return false;

        Set<Character> validChars = Set.of(
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');

        for (int i = 0; i < length; i++) {
            char ch = color.charAt(i);
            if (!validChars.contains(ch))
                return false;
        }

        return true;
    }

    private String getHex(String obj) {
        return obj.replace("#", "").replace("0x", "").toUpperCase();
    }

}
