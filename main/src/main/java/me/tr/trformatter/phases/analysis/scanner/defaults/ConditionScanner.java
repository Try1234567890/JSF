package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawCondition;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ConditionScanner extends GenericScanner {
    public static final ConditionScanner INSTANCE = new ConditionScanner();

    public ConditionScanner(Characters chars) {
        super(
                chars != null ? chars.getOpenCondition() : Characters.DEF_OPEN_CONDITION,
                chars != null ? chars.getCloseCondition() : Characters.DEF_CLOSE_CONDITION,
                chars
        );
    }

    public ConditionScanner() {
        this(new Characters());
    }
    
    /**
     * Scans the text and returns a list specifically of {@link IndexedRawCondition}.
     *
     * @param text  The source text to analyze.
     * @param from  The starting index.
     * @param end   The ending index (-1 for end of string).
     * @param depth The recursion depth.
     * @return A list of identified tags.
     */
    @Override
    public List<IndexedRawCondition> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawCondition.class::isInstance)
                .map(IndexedRawCondition.class::cast)
                .toList();
    }

    public List<IndexedRawCondition> scan(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawCondition.class::isInstance)
                .map(IndexedRawCondition.class::cast)
                .toList();
    }

    public List<IndexedRawCondition> scan(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawCondition.class::isInstance)
                .map(IndexedRawCondition.class::cast)
                .toList();
    }

    public List<IndexedRawCondition> scan(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawCondition.class::isInstance)
                .map(IndexedRawCondition.class::cast)
                .toList();
    }

    public List<IndexedRawCondition> scanMax(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawCondition.class::isInstance)
                .map(IndexedRawCondition.class::cast)
                .toList();
    }

    public List<IndexedRawCondition> scanMax(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawCondition.class::isInstance)
                .map(IndexedRawCondition.class::cast)
                .toList();
    }

    public Optional<IndexedRawCondition> findFirst(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawCondition)
                .map(IndexedRawCondition.class::cast);
    }

    public Optional<IndexedRawCondition> findLast(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawCondition)
                .map(IndexedRawCondition.class::cast);
    }

    public Stream<IndexedRawCondition> stream(String text) {
        return super.stream(text)
                .filter((component) -> component instanceof IndexedRawCondition)
                .map(IndexedRawCondition.class::cast);
    }

    @Override
    public IndexedRawCondition create(String text, int start, int end) {
        IndexedRawParams params = ParamsScanner.INSTANCE.scanParamsOrNull(text);

        return new IndexedRawCondition(text, params, start, end);
    }
}
