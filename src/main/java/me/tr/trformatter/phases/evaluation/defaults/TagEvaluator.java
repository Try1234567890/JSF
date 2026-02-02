package me.tr.trformatter.phases.evaluation.defaults;

import me.tr.trformatter.phases.evaluation.Evaluator;
import me.tr.trformatter.phases.evaluation.components.EvalTag;

import java.util.ArrayList;
import java.util.List;

public class TagEvaluator implements Evaluator<EvalTag, String> {
    public static final TagEvaluator INSTANCE = new TagEvaluator();

    public List<String> evaluate(List<EvalTag> tags) {
        List<String> results = new ArrayList<>();

        for (EvalTag tag : tags) {
            String tagResult = evaluate(tag);

            results.add(tagResult);
        }

        return results;
    }

    @Override
    public String evaluate(EvalTag component) {
        if (component == null) {
            throw new NullPointerException("The component is null");
        }
        return component.getTag().evaluate(component.getParams());
    }
}
