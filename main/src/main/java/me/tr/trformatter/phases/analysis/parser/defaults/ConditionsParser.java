package me.tr.trformatter.phases.analysis.parser.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.parser.Parser;
import me.tr.trformatter.components.Condition;
import me.tr.trformatter.phases.evaluation.components.EvalCondition;

import java.util.ArrayList;
import java.util.List;

public class ConditionsParser implements Parser<ConditionToken> {
    public static final ConditionsParser INSTANCE = new ConditionsParser();

    @Override
    public EvalCondition parse(ConditionToken token) {
        if (token == null) return null;

        Condition condition = Condition.getCondition(token.getName().getName()).orElse(null);
        return condition != null ? new EvalCondition(condition, token.params()) : null;
    }

    public List<EvalCondition> parseAll(ConditionToken token) {
        if (token == null) return null;

        List<EvalCondition> evalList = new ArrayList<>();
        ConditionToken current = token;

        while (current != null) {
            EvalCondition eval = parse(current);
            if (eval != null) {
                if (current.getOperator() != null) {
                    eval.setOperator(current.getOperator().getOperator());
                }
                evalList.add(eval);
            }
            current = current.getRight();
        }

        return evalList;
    }
}
