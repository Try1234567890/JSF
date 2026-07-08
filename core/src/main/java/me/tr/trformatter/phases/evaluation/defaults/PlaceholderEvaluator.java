package me.tr.trformatter.phases.evaluation.defaults;

import me.tr.trformatter.phases.evaluation.Evaluator;
import me.tr.trformatter.phases.evaluation.components.EvalPlaceholder;
import me.tr.trformatter.phases.synthesis.interpolator.components.IndexedPlaceholder;
import me.tr.trformatter.utility.Validator;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderEvaluator implements Evaluator<EvalPlaceholder, IndexedPlaceholder> {
    public static final PlaceholderEvaluator INSTANCE = new PlaceholderEvaluator();

    public List<IndexedPlaceholder> evaluate(List<EvalPlaceholder> placeholders) {
        List<IndexedPlaceholder> results = new ArrayList<>();

        for (EvalPlaceholder placeholder : placeholders) {
            IndexedPlaceholder plResult = evaluate(placeholder);

            results.add(plResult);
        }

        return results;
    }

    @Override
    public IndexedPlaceholder evaluate(EvalPlaceholder component) {
        if (Validator.isNull(component)) {
            throw new NullPointerException("The placeholder is null");
        }

        if (component.hasConditions()) {
            if (!ConditionsEvaluator.INSTANCE.evaluate(component.getConditions()))
                return new IndexedPlaceholder(component.start(), component.end(), "");
        }

        return new IndexedPlaceholder(component.start(), component.end(), TagEvaluator.INSTANCE.evaluate(component.getTag()));
    }
}
