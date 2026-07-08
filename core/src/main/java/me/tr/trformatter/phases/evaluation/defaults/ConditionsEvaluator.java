package me.tr.trformatter.phases.evaluation.defaults;

import me.tr.trformatter.phases.evaluation.Evaluator;
import me.tr.trformatter.phases.evaluation.components.EvalCondition;

import java.util.List;

public class ConditionsEvaluator implements Evaluator<EvalCondition, Boolean> {
    public static final ConditionsEvaluator INSTANCE = new ConditionsEvaluator();


    public boolean evaluate(List<EvalCondition> conditions) {
        if (conditions == null
                || conditions.isEmpty()) {
            return false;
        }


        if (conditions.size() == 1) {
            return evaluate(conditions.getFirst());
        }

        EvalCondition left = conditions.removeFirst();
        EvalCondition right = conditions.remove(1);

        while (!conditions.isEmpty() && right != null) {
            if (!resolve(left, right)) {
                return false;
            }

            left = right;
            right = conditions.removeFirst();
        }

        return true;
    }

    private boolean resolve(EvalCondition left, EvalCondition right) {
        boolean leftEval = evaluate(left);
        boolean rightEval = evaluate(right);

        return left.getOperator().apply(leftEval, rightEval);
    }

    @Override
    public Boolean evaluate(EvalCondition component) {
        if (component == null) {
            throw new NullPointerException("Condition is null");
        }

        return component.getCondition().evaluate(component.getParams());
    }

}
