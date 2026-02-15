package me.tr.trformatter.strings.color.ansi;

import me.tr.trformatter.strings.color.converter.ColorConverter;

public interface ANSI {

    String getName();

    int getCode();

    int getR();

    int getG();

    int getB();

    String getTextEscapeSequence();

    String getBackgroundEscapeSequence();

    static <T extends ANSI> T getNearest(String hex, T[] values) {
        int[] rgb = ColorConverter.getHexConverter().toRGBOrThrown(hex);

        int dR = rgb[0];
        int dG = rgb[1];
        int dB = rgb[2];

        T nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (T color : values) {
            int r = color.getR();
            int g = color.getG();
            int b = color.getB();

            int rDiff = r - dR;
            int gDiff = g - dG;
            int bDiff = b - dB;

            double distance = (rDiff * rDiff) + (gDiff * gDiff) + (bDiff * bDiff);

            if (distance < minDistance) {
                minDistance = distance;
                nearest = color;
            }
        }

        return nearest;
    }
}
