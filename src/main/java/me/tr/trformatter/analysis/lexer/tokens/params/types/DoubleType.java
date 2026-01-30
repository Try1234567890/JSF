package me.tr.trformatter.analysis.lexer.tokens.params.types;

public class DoubleType implements ParamType<Double> {

    @Override
    public boolean isCorrectType(String obj) {
        try {
            String[] parts = obj.split("\\.");

            if (parts.length != 2) {
                return false;
            }
            String decimal = parts[1];

            for (char c : decimal.toCharArray()) {
                if (!Character.isDigit(c))
                    return false;
            }

            return decimal.length() >= 8 && decimal.length() <= 15;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Double convert(String obj) {
        return Double.parseDouble(obj);
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}
