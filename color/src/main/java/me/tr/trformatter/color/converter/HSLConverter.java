package me.tr.trformatter.color.converter;

import me.tr.trformatter.color.exceptions.ColorConversionException;
import me.tr.trformatter.color.validator.HSB_HSLValidator;

import java.util.Arrays;

public class HSLConverter implements ColorConverter<double[]> {
    protected HSLConverter() {
    }

    private record Holder() {
        private static final HSLConverter INSTANCE = new HSLConverter();
    }

    public static HSLConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public int[] toARGB(double[] color) throws ColorConversionException {
        try {
            double[] hsb = HSB_HSLValidator.getInstance().validate(color);

            double h = hsb[0];
            double s = hsb[1];
            double l = hsb[2];

            double hF = (h / 60.0) % 6.0; // Convert hue to a normalized angle. This is a value between 0 and 5
            if (hF < 0) hF += 6.0; // Protection against negative floating-point values

            double c = (1.0 - Math.abs((2.0 * l) - 1.0)) * s;
            double x = c * (1.0 - Math.abs((hF % 2.0) - 1.0));

            double rF, gF, bF;

            switch ((int) hF) {
                case 0 -> {
                    rF = c;
                    gF = x;
                    bF = 0;
                }
                case 1 -> {
                    rF = x;
                    gF = c;
                    bF = 0;
                }
                case 2 -> {
                    rF = 0;
                    gF = c;
                    bF = x;
                }
                case 3 -> {
                    rF = 0;
                    gF = x;
                    bF = c;
                }
                case 4 -> {
                    rF = x;
                    gF = 0;
                    bF = c;
                }
                case 5 -> {
                    rF = c;
                    gF = 0;
                    bF = x;
                }
                default -> throw new ColorConversionException("Invalid hue value: " + h);
            }

            double m = l - (c / 2.0);

            int r = Math.clamp(Math.round((rF + m) * 255.0), 0, 255);
            int g = Math.clamp(Math.round((gF + m) * 255.0), 0, 255);
            int b = Math.clamp(Math.round((bF + m) * 255.0), 0, 255);

            return new int[]{255, r, g, b};
        } catch (Throwable t) {
            throw new ColorConversionException("Color " + Arrays.toString(color) + " conversion failed.", t);
        }
    }

    @Override
    public double[] fromARGB(int[] color) throws ColorConversionException {
        double[] argb = ARGBConverter.normalize(color);
        double r = argb[1];
        double g = argb[2];
        double b = argb[3];

        double xMax = Math.max(r, Math.max(g, b));
        double xMin = Math.min(r, Math.min(g, b));

        double c = xMax - xMin;
        double l = (xMax + xMin) / 2.0;
        double h = getHue(c, xMax, r, g, b);
        double s = getSaturation(c, xMax, l);

        return new double[]{h, s, l};
    }

    protected double getSaturation(double c, double v, double l) {
        if (l == 0.0 || l == 1.0) {
            return 0;
        } else {
            double den = 1.0 - Math.abs((2.0 * l) - 1.0);
            return (c / den);
        }
    }

    private double getHue(double c, double v, double r, double g, double b) {
        if (c == 0.0) return 0;

        double hUnscaled;
        if (v == r) {
            hUnscaled = ((g - b) / c) % 6.0;
        } else if (v == g) {
            hUnscaled = ((b - r) / c) + 2.0;
        } else {
            hUnscaled = ((r - g) / c) + 4.0;
        }

        double h = hUnscaled * 60.0;
        if (h < 0.0) h += 360;
        return h;
    }

    @Override
    public double[] fromHSL(double[] hsl) throws ColorConversionException {
        return HSB_HSLValidator.getInstance().validate(hsl);
    }

    @Override
    public double[] toHSL(double[] color) throws ColorConversionException {
        return HSB_HSLValidator.getInstance().validate(color);
    }
}