package me.tr.trformatter.phases.analysis.parser.defaults;

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
        EvalTag tag = new TagParser().parse(token.getTag());
        List<EvalCondition> conditions = new ConditionsParser().parseAll(token.getConditions());

        if (tag == null) {
            throw new NullPointerException("The tag with ID: " + token.getTag().getName().getName() + " is not found. Make sure you have registered it.");
        }

        return new EvalPlaceholder(tag, conditions, token.start(), token.end());
    }

    public List<EvalPlaceholder> parse(List<PlaceholderToken> list) {
        List<EvalPlaceholder> components = new ArrayList<>();

        for (PlaceholderToken token : list) {
            components.add(parse(token));
        }

        return components;
    }
}
