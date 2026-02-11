package me.tr.trformatter.phases.analysis.lexer.tokens.params.types;

public class StringType implements ParamType<String> {
    public static final StringType TYPE = new StringType();

    @Override
    public boolean isCorrectType(String obj) {
        return (obj.startsWith("\"") || obj.startsWith("'"))
                && (obj.endsWith("\"") || obj.endsWith("'"));
    }

    @Override
    public String convert(String obj) {
        return obj.substring(1, obj.length() - 1);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
