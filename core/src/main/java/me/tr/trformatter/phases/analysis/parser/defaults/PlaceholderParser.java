package me.tr.trformatter.phases.analysis.parser.defaults;

import me.tr.trformatter.phases.analysis.exceptions.ComponentNotFound;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.parser.Parser;
import me.tr.trformatter.phases.evaluation.components.EvalCondition;
import me.tr.trformatter.phases.evaluation.components.EvalPlaceholder;
import me.tr.trformatter.phases.evaluation.components.EvalTag;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderParser implements Parser<PlaceholderToken> {
    public static final PlaceholderParser INSTANCE = new PlaceholderParser();

    @Override
    public EvalPlaceholder parse(PlaceholderToken token) {
        EvalTag tag = TagParser.INSTANCE.parse(token.getTag());

        if (tag == null) {
            return null;
        }

        List<EvalCondition> conditions = ConditionsParser.INSTANCE.parseAll(token.getConditions());

        return new EvalPlaceholder(tag, conditions, token.start(), token.end());
    }

    public List<EvalPlaceholder> parseAll(List<PlaceholderToken> list) {
        List<EvalPlaceholder> components = new ArrayList<>();

        for (PlaceholderToken token : list) {
            EvalPlaceholder placeholder = parse(token);

            // Already logged any error
            if (placeholder == null) continue;

            components.add(placeholder);
        }

        return components;
    }
}
