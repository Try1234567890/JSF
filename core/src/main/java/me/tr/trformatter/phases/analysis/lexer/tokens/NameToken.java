package me.tr.trformatter.phases.analysis.lexer.tokens;

public class NameToken implements Token {
    private final String name;

    public NameToken(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String asString() {
        return name;
    }


    @Override
    public String toString() {
        return asString();
    }
}
