package me.tr.trformatter.phases.analysis.lexer.tokens.params.types;

public class BooleanType implements ParamType<Boolean> {

    @Override
    public boolean isCorrectType(String obj) {
        return (obj.equalsIgnoreCase("true") || obj.equalsIgnoreCase("false")) ||
                (obj.equalsIgnoreCase("yes") || obj.equalsIgnoreCase("no"));
    }

    @Override
    public Boolean convert(String obj) {
        return (obj.equalsIgnoreCase("true") || obj.equalsIgnoreCase("yes"));
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.class;
    }
}
