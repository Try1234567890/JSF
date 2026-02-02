package me.tr.trformatter.phases.evaluation.defaults;

import me.tr.trformatter.phases.evaluation.Evaluator;
import me.tr.trformatter.phases.evaluation.components.EvalFunction;

import java.util.List;

public class FunctionEvaluator implements Evaluator<EvalFunction, String> {
    private final String tagResult;

    public FunctionEvaluator(String tagResult) {
        this.tagResult = tagResult;
    }

    public String getTagResult() {
        return tagResult;
    }

    public String evaluate(List<EvalFunction> functions) {
        String result = getTagResult();

        for (EvalFunction function : functions) {
            result = evaluate(function);
        }

        return result;
    }

    @Override
    public String evaluate(EvalFunction component) {
        if (component == null) {
            throw new NullPointerException("The component is null");
        }

        return component.getFunction().evaluate(getTagResult(), component.getParams());
    }
}
