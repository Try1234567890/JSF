package me.tr.trformatter.analysis.lexer.tokens.params.types;

public class FloatType implements ParamType<Float> {

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

            return decimal.length() <= 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Float convert(String obj) {
        return Float.parseFloat(obj);
    }

    @Override
    public Class<Float> getType() {
        return Float.class;
    }
}
