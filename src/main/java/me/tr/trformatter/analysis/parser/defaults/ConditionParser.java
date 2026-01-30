package me.tr.trformatter.analysis.parser.defaults;

import me.tr.trformatter.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.analysis.parser.Parser;
import me.tr.trformatter.components.Condition;
import me.tr.trformatter.evaluation.components.EvalCondition;

import java.util.ArrayList;
import java.util.List;

public class ConditionParser implements Parser<ConditionToken> {

    @Override
    public EvalCondition parse(ConditionToken token) {


        Condition condition = Condition.getCondition(token.getName().getName()).orElse(null);
        return condition != null ? new EvalCondition(condition, token.params()) : null;
    }

    public List<EvalCondition> parseAll(ConditionToken first) {
        List<EvalCondition> conditions = new ArrayList<>();

        ConditionToken token = first;

        if (token == null) {

            return conditions;
        }

        do {
            EvalCondition condition = parse(token);

            if (condition != null) {
                conditions.add(condition);
            }
        } while ((token = token.getRight()) != null);

        return conditions;
    }
}
