package me.tr.trformatter.evaluation.components;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Tag;

import java.util.List;

public class EvalPlaceholder implements EvalComponent {
    private final EvalTag tag;
    private final List<EvalCondition> conditions;

    public EvalPlaceholder(EvalTag tag, List<EvalCondition> conditions) {
        this.tag = tag;
        this.conditions = conditions;
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
        if (hasConditions()) {
            for (EvalCondition condition : conditions) {
                if (!condition.evaluate())
                    return "";
            }
        }

        return tag.evaluate();
    }

    @Override
    public String toString() {
        return evaluate();
    }
}
