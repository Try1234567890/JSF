package me.tr.trformatter.phases.analysis.lexer.tokens;

import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;

import java.util.function.BiFunction;

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

        AND((b, b1) -> b && b1),
        OR((b, b1) -> b || b1);

        private final BiFunction<Boolean, Boolean, Boolean> operator;

        Operator(BiFunction<Boolean, Boolean, Boolean> operator) {
            this.operator = operator;
        }

        public boolean apply(Boolean left, Boolean right) {
            return operator.apply(left, right);
        }
    }
}
