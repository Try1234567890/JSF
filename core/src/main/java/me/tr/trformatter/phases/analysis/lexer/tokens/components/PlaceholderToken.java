package me.tr.trformatter.phases.analysis.lexer.tokens.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.Token;

public class PlaceholderToken implements Token {
    private final TagToken tag;
    private final ConditionToken conditions;
    private final int start;
    private final int end;

    public PlaceholderToken(TagToken tag, ConditionToken conditions, int start, int end) {
        this.tag = tag;
        this.conditions = conditions;
        this.start = start;
        this.end = end;
    }


    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public TagToken getTag() {
        return tag;
    }

    public ConditionToken getConditions() {
        return conditions;
    }

    public boolean hasConditions() {
        return conditions != null;
    }

    @Override
    public String asString() {
        return tag.asString() + (hasConditions() ? " " + conditions.asString() : "");
    }

    @Override
    public String toString() {
        return asString();
    }
}
