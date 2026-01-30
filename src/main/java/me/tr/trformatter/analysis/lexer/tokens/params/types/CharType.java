package me.tr.trformatter.analysis.lexer.tokens.params.types;

public class CharType implements ParamType<Character> {

    @Override
    public boolean isCorrectType(String obj) {
        if ((obj.startsWith("\"") || obj.startsWith("'"))
                && (obj.endsWith("\"") || obj.endsWith("'"))) {
            return obj.length() == 3;
        }

        return false;
    }

    @Override
    public Character convert(String obj) {
        return obj.charAt(1);
    }

    @Override
    public Class<Character> getType() {
        return Character.class;
    }
}
