package me.tr.formatter.strings.color;

import me.tr.formatter.TrValidator;

import java.util.Arrays;

public class TrColor {
    private static final String ESC = "\u001B";
    private final String hex;
    private final int[] argb;

    public TrColor(String hex) {
        this.hex = hex;
        this.argb = toARGB(hex);
    }

    public TrColor(int[] argb) {
        this.hex = toHex(argb);
        this.argb = argb;
    }

    public int[] getArgb() {
        return argb;
    }

    public String getHex() {
        return hex;
    }

    public String toMiniMessage() {
        return "<" + hex + ">";
    }

    public String toANSIForeground() {
        return ESC + "[38;2;" + getArgb()[0] + ";" + getArgb()[1] + ";" + getArgb()[2] + "m";
    }

    public String toANSIBackground() {
        return ESC + "[48;2;" + getArgb()[0] + ";" + getArgb()[1] + ";" + getArgb()[2] + "m";
    }

    public static String ansiReset() {
         return ESC + "[0m";
    }

    public String applyAsMiniMessage(String str) {
        return "<" + hex + ">" + str;
    }

    public String applyAsANSIForeground(String str) {
        return ESC + "[38;2;" + getArgb()[0] + ";" + getArgb()[1] + ";" + getArgb()[2] + "m" + str + ESC + "[0m";
    }

    public String applyAsANSIBackground(String str) {
        return ESC + "[48;2;" + getArgb()[0] + ";" + getArgb()[1] + ";" + getArgb()[2] + "m" + str + ESC + "[0m";
    }

    private String toHex(int[] argb) {
        TrValidator.isNull(argb, "ARGB to convert is null.");
        if (argb.length != 4) {
            throw new IllegalArgumentException("ARGB " + Arrays.toString(argb) + " length to convert is incorrect.");
        }

        int a = argb[0];
        int r = argb[1];
        int g = argb[2];
        int b = argb[3];

        if (a < 0 || a > 255) {
            throw new IllegalArgumentException("ARGB to convert has " + a + " of transparency.");
        } else if (r < 0 || r > 255) {
            throw new IllegalArgumentException("ARGB to convert has " + r + " of red.");
        } else if (g < 0 || g > 255) {
            throw new IllegalArgumentException("ARGB to convert has " + g + " of green.");
        } else if (b < 0 || b > 255) {
            throw new IllegalArgumentException("ARGB to convert has " + b + " of blue.");
        }

        return "#%02X%02X%02X%02X".formatted(a, r, g, b);
    }

    private int[] toARGB(String hexadecimal) {
        TrValidator.isNull(hexadecimal, "Hexadecimal to convert is null.");
        if (hexadecimal.length() != 8) {
            throw new IllegalArgumentException("Hexadecimal " + hexadecimal + " length to convert is incorrect.");
        }

        int a = Integer.parseInt(hexadecimal.substring(0, 2), 16);
        int r = Integer.parseInt(hexadecimal.substring(2, 4), 16);
        int g = Integer.parseInt(hexadecimal.substring(4, 6), 16);
        int b = Integer.parseInt(hexadecimal.substring(6, 8), 16);

        return new int[]{a, r, g, b};
    }
}
