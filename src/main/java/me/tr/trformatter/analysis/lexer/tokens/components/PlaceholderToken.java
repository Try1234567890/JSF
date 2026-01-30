package me.tr.trformatter.analysis.lexer.tokens.components;

import me.tr.trformatter.analysis.lexer.tokens.Token;

public class PlaceholderToken implements Token {
    private final TagToken tag;
    private final ConditionToken conditions;

    public PlaceholderToken(TagToken tag, ConditionToken conditions) {
        this.tag = tag;
        this.conditions = conditions;
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
