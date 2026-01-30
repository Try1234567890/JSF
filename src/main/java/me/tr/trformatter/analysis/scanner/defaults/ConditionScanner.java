package me.tr.trformatter.analysis.scanner.defaults;

import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawCondition;
import me.tr.trformatter.analysis.scanner.results.IndexedRawParam;
import me.tr.trformatter.analysis.scanner.results.IndexedRawParams;
import me.tr.trformatter.strings.CString;

import java.util.List;

public class ConditionScanner extends GenericScanner {
    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenCondition() : Characters.DEF_OPEN_CONDITION;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getCloseCondition() : Characters.DEF_CLOSE_CONDITION;
    }


    public ConditionScanner(String text, Characters chars) {
        super(text, OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public ConditionScanner(String text) {
        this(text, new Characters());
    }

    public List<IndexedRawCondition> scan(int from, int end, int depth) {
        return super.scan(from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawCondition)
                .map(comp -> (IndexedRawCondition) comp)
                .toList();
    }

    public List<IndexedRawCondition> scan(int start, int depth) {
        return scan(start, -1, depth);
    }

    public List<IndexedRawCondition> scan(int start) {
        return scan(start, -1);
    }

    public List<IndexedRawCondition> scan() {
        return scan(-1, -1);
    }

    @Override
    public IndexedRawCondition create(String text, int start, int end) {
        IndexedRawParams params = new ParamsScanner(text).scanParams();

        return new IndexedRawCondition(text, params, start, end);
    }
}
