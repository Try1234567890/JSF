package me.tr.trformatter.evaluation;

import me.tr.trformatter.evaluation.components.EvalPlaceholder;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderEvaluator implements Evaluator {

    public List<String> evaluate(List<EvalPlaceholder> placeholders) {
        List<String> result = new ArrayList<>();

        for (EvalPlaceholder placeholder : placeholders) {
            result.add(placeholder.evaluate());
        }

        return result;
    }

}
