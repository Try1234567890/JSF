package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.OperatorToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawCondition;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawConditions;
import me.tr.trformatter.strings.CString;

import java.util.List;

public class ConditionsLexer extends GenericLexer<IndexedRawConditions> {
    public static final ConditionsLexer INSTANCE = new ConditionsLexer();

    public ConditionsLexer(Characters characters) {
        super(characters);
    }

    public ConditionsLexer() {
        super();
    }


    @Override
    public ConditionToken tokenize(IndexedRawConditions rawConditions) {
        List<IndexedRawCondition> conditions = rawConditions.conditions();

        CString firstComponent = new CString(rawConditions.component());
        if (conditions.isEmpty()) return null;

        IndexedRawCondition firstRaw = conditions.getFirst();
        ConditionToken head = createToken(new CString(firstRaw.component()), firstRaw);
        ConditionToken currentLeft = head;

        for (int i = 1; i < conditions.size(); i++) {
            IndexedRawCondition nextRaw = conditions.get(i);

            CString nextComponent = new CString(nextRaw.component());
            OperatorToken.Operator operator = getOperator(firstComponent, conditions.get(i - 1).end(), nextRaw.start());

            ConditionToken nextToken = createToken(nextComponent, nextRaw);
            currentLeft.associate(operator, nextToken);
            currentLeft = nextToken;
        }

        return head;
    }

    private ConditionToken createToken(CString component, IndexedRawCondition raw) {
        NameToken name = getName(component);
        List<ParamToken> params = getParams(raw);
        return new ConditionToken(name, params);
    }

    private OperatorToken.Operator getOperator(CString component, int start, int end) {
        int and = component.indexOfIgnoringStrings(characters().getAndCondition(), start, end);

        return and != -1 ? OperatorToken.Operator.AND : OperatorToken.Operator.OR;
    }
}
