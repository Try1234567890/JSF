package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawCondition;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;

import java.util.List;

public class ConditionScanner extends GenericScanner {
    public static final ConditionScanner INSTANCE = new ConditionScanner();

    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenCondition() : Characters.DEF_OPEN_CONDITION;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getCloseCondition() : Characters.DEF_CLOSE_CONDITION;
    }


    public ConditionScanner(Characters chars) {
        super(OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public ConditionScanner() {
        this(new Characters());
    }

    public IndexedRawCondition scanOne(String text, int from, int end) {
        List<IndexedRawCondition> conditions = scan(text, from, end, 1);
        return conditions.isEmpty() ? null : conditions.getFirst();
    }

    public IndexedRawCondition scanOne(String text, int start) {
        return scanOne(text, start, -1);
    }

    public IndexedRawCondition scanOne(String text) {
        return scanOne(text, -1, -1);
    }

    public List<IndexedRawCondition> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawCondition)
                .map(comp -> (IndexedRawCondition) comp)
                .toList();
    }

    public List<IndexedRawCondition> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    public List<IndexedRawCondition> scan(String text, int start) {
        return scan(text, start, -1);
    }

    public List<IndexedRawCondition> scan(String text) {
        return scan(text, -1, -1);
    }

    @Override
    public IndexedRawCondition create(String text, int start, int end) {
        IndexedRawParams params = ParamsScanner.INSTANCE.scanParams(text);

        return new IndexedRawCondition(text, params, start, end);
    }
}
