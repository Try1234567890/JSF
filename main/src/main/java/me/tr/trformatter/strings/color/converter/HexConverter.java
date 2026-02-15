package me.tr.trformatter.strings.color.converter;

import java.util.Optional;

public class HexConverter extends ColorConverter {


    @Override
    public Optional<int[]> toARGB(Object object) {
        if (!getValidator().validate(object)) {
            return Optional.empty();
        }
        String hex = getHex((String) object);

        if (hasAlpha(hex)) {
            String a = hex.substring(0, 2);
            String r = hex.substring(2, 4);
            String g = hex.substring(4, 6);
            String b = hex.substring(6, 8);
            return toARGB(a, r, g, b);
        } else {
            String r = hex.substring(0, 2);
            String g = hex.substring(2, 4);
            String b = hex.substring(4, 6);
            return toARGB("FF", r, g, b);
        }
    }

    @Override
    public Optional<String> fromARGB(int[] argb) {
        if (!getARGBValidator().validate(argb)) {
            return Optional.empty();
        }
        int a = argb[0];
        int r = argb[1];
        int g = argb[2];
        int b = argb[3];

        return Optional.of(String.format("%02X%02X%02X%02X", a, r, g, b));
    }

    private boolean hasAlpha(String hex) {
        return hex.length() == 8;
    }

    private String getHex(String obj) {
        return obj.replace("#", "").replace("0x", "").toUpperCase();
    }

    private Optional<int[]> toARGB(String aStr, String rStr, String gStr, String bStr) {
        int a = Integer.parseInt(aStr, 16);
        int r = Integer.parseInt(rStr, 16);
        int g = Integer.parseInt(gStr, 16);
        int b = Integer.parseInt(bStr, 16);
        return Optional.of(new int[]{a, r, g, b});
    }
}










