package me.tr.trformatter.evaluation.components;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Condition;

public class EvalCondition implements EvalComponent {
    private final Condition condition;
    private final ParamsContainer params;

    public EvalCondition(Condition condition, ParamsContainer params) {
        this.condition = condition;
        this.params = params;
    }

    public Condition getCondition() {
        return condition;
    }

    public ParamsContainer getParams() {
        return params;
    }

    public boolean evaluate() {
        return condition.evaluate(params);
    }
}
