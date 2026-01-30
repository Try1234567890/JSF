package me.tr.trformatter.analysis.lexer.tokens.params.types;

public class ShortType implements ParamType<Short> {

     @Override
    public boolean isCorrectType(String obj) {
        try {
            long parsed = Long.parseLong(obj);
            return parsed >= Short.MIN_VALUE && parsed <= Short.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Short convert(String obj) {
        return Short.parseShort(obj);
    }

    @Override
    public Class<Short> getType() {
        return Short.class;
    }
}
