package me.tr.trformatter.phases.analysis.lexer.tokens.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.OperatorToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.Token;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.Parameterized;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsMapper;

import java.util.List;

public class ConditionToken implements Token, Parameterized {
    private final NameToken name;
    private final List<ParamToken> params;
    private final ParamsContainer paramsContainer;
    private OperatorToken operator;

    public ConditionToken(NameToken name, List<ParamToken> params) {
        this.name = name;
        this.params = params;
        this.paramsContainer = ParamsMapper.map(params);
    }

    public NameToken getName() {
        return name;
    }

    public List<ParamToken> getParams() {
        return params;
    }

    public OperatorToken getOperator() {
        return operator;
    }


    public ConditionToken getLeft() {
        return this;
    }

    public ConditionToken getRight() {
        return operator == null ? null : operator.getRight();
    }

    public ConditionToken associate(OperatorToken.Operator operator, ConditionToken second) {
        this.operator = new OperatorToken(operator, second);
        return second;
    }

    public ConditionToken and(ConditionToken second) {
        return associate(OperatorToken.Operator.AND, second);
    }

    public ConditionToken or(ConditionToken second) {
        return associate(OperatorToken.Operator.OR, second);
    }

    @Override
    public ParamsContainer params() {
        return paramsContainer;
    }

    public boolean hasParams() {
        return params != null && !params.isEmpty();
    }

    @Override
    public String asString() {
        return "@[" + name.asString() + (hasParams() ? "(" + String.join(",", getParams().stream().map(ParamToken::asString).toList()) + ")" : "") + "] "
                + (getOperator() != null ? getOperator().getOperator().name() + (getRight() != null ? " " + getRight().asString() : "") : "");
    }


    @Override
    public String toString() {
        return asString();
    }
}
