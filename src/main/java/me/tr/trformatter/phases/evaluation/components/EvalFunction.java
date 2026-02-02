package me.tr.trformatter.phases.evaluation.components;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.phases.evaluation.defaults.FunctionEvaluator;

public class EvalFunction implements EvalComponent {
    private final Function function;
    private final ParamsContainer params;

    public EvalFunction(Function function, ParamsContainer params) {
        this.function = function;
        this.params = params;
    }

    public Function getFunction() {
        return function;
    }

    public ParamsContainer getParams() {
        return params;
    }
}
