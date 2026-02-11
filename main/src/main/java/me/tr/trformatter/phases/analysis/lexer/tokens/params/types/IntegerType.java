package me.tr.trformatter.phases.analysis.lexer.tokens.params.types;

public class IntegerType implements ParamType<Integer> {

     @Override
    public boolean isCorrectType(String obj) {
        try {
            long parsed = Long.parseLong(obj);
            return parsed >= Integer.MIN_VALUE && parsed <= Integer.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Integer convert(String obj) {
        return Integer.parseInt(obj);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
