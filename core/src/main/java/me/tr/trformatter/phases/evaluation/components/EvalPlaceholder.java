package me.tr.trformatter.phases.evaluation.components;

import me.tr.trformatter.phases.evaluation.defaults.PlaceholderEvaluator;

import java.util.List;

public class EvalPlaceholder implements EvalComponent {
    private final EvalTag tag;
    private final List<EvalCondition> conditions;
    private final int start;
    private final int end;

    public EvalPlaceholder(EvalTag tag, List<EvalCondition> conditions, int start, int end) {
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

    public EvalTag getTag() {
        return tag;
    }

    public List<EvalCondition> getConditions() {
        return conditions;
    }

    public boolean hasConditions() {
        return conditions != null && !conditions.isEmpty();
    }


    public String evaluate() {
        return PlaceholderEvaluator.INSTANCE.evaluate(this).result();
    }

    @Override
    public String toString() {
        return evaluate();
    }
}
