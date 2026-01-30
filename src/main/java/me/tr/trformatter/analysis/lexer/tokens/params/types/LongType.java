package me.tr.trformatter.analysis.lexer.tokens.params.types;

public class LongType implements ParamType<Long> {

    @Override
    public boolean isCorrectType(String obj) {
        try {
            long parsed = Long.parseLong(obj);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Long convert(String obj) {
        return Long.parseLong(obj);
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }
}
