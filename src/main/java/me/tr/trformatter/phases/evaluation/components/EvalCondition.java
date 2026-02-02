package me.tr.trformatter.phases.evaluation.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.OperatorToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Condition;
import me.tr.trformatter.phases.evaluation.defaults.ConditionsEvaluator;

public class EvalCondition implements EvalComponent {
    private final Condition condition;
    private final ParamsContainer params;
    private OperatorToken.Operator operator;

    public EvalCondition(Condition condition, ParamsContainer params) {
        this.condition = condition;
        this.params = params;
    }

    public void setOperator(OperatorToken.Operator operator) {
        this.operator = operator;
    }

    public OperatorToken.Operator getOperator() {
        return operator;
    }


    public Condition getCondition() {
        return condition;
    }

    public ParamsContainer getParams() {
        return params;
    }

    public boolean evaluate() {
        return ConditionsEvaluator.INSTANCE.evaluate(this);
    }
}