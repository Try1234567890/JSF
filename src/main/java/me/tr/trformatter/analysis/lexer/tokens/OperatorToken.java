package me.tr.trformatter.analysis.lexer.tokens;

import me.tr.trformatter.analysis.lexer.tokens.components.ConditionToken;

public class OperatorToken implements Token {
    private final Operator operator;
    private final ConditionToken right;

    public OperatorToken(Operator operator, ConditionToken right) {
        this.operator = operator;

        this.right = right;
    }

    public ConditionToken getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String asString() {
        return operator.toString();
    }


    public enum Operator {

        AND,
        OR

    }
}
