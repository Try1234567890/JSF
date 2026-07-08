package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawCondition;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ConditionScanner extends GenericScanner {
    /**
     * Singleton instance for default scanning operations.
     */
    public static final ConditionScanner INSTANCE =
            new ConditionScanner(CharacterSet.DEF_OPEN_CONDITION.getDelimiter(),
                    CharacterSet.DEF_CLOSE_CONDITION.getDelimiter(), CharacterSet.DEFAULT);

    /**
     * Initializes a new GenericScanner with specified delimiters and character rules.
     *
     * @param openDel    The string sequence identifying the start of a component.
     * @param closeDel   The string sequence identifying the end of a component.
     * @param characters The configuration for special characters. If null, default characters are used.
     * @throws NullPointerException if openDel or closeDel are null.
     */
    protected ConditionScanner(String openDel, String closeDel, CharacterSet characters) {
        super(openDel, closeDel, characters);
    }


    /**
     * Constructs a ConditionScanner with specific character delimiters.
     *
     * @param chars The character configuration to use. If null, default tags are used.
     */
    public static ConditionScanner of(CharacterSet chars) {
        if (chars == null
                || chars.isDefault()) {
            return INSTANCE;
        }
        return new ConditionScanner(
                chars.getOpenCondition(),
                chars.getCloseCondition(),
                chars
        );
    }

    public static ConditionScanner of() {
        return INSTANCE;
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
