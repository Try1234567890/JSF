package me.tr.trformatter.analysis.lexer.tokens.params.types;

public interface ParamType<T> {

    boolean isCorrectType(String obj);

    T convert(String obj);

    Class<T> getType();

}
