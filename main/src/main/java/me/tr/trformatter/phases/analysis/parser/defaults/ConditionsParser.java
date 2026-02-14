package me.tr.trformatter.phases.analysis.parser.defaults;

import me.tr.trformatter.phases.analysis.exceptions.ComponentNotFound;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.parser.Parser;
import me.tr.trformatter.components.Condition;
import me.tr.trformatter.phases.evaluation.components.EvalCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConditionsParser implements Parser<ConditionToken> {
    public static final ConditionsParser INSTANCE = new ConditionsParser();

    @Override
    public EvalCondition parse(ConditionToken token) {
        if (token == null) return null;

        Optional<Condition> conditionOpt = Condition.getCondition(token.getName().getName());

        return conditionOpt.map(value -> new EvalCondition(value, token.params()))
                .orElse(null);
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
            } else {
                ComponentNotFound.condition(token.getName().getName())
                        .printStackTrace(System.err);
            }

            current = current.getRight();
        }

        return evalList;
    }
}
