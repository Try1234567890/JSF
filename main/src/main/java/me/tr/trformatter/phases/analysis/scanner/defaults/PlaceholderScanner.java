package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawConditions;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawTag;
import me.tr.trformatter.strings.CString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PlaceholderScanner extends GenericScanner {
    public static final PlaceholderScanner INSTANCE = new PlaceholderScanner();


    public PlaceholderScanner(Characters chars) {
        super(
                chars != null ? chars.getOpenPlaceholder() : Characters.DEF_OPEN_PLACEHOLDER,
                chars != null ? chars.getClosePlaceholder() : Characters.DEF_CLOSE_PLACEHOLDER,
                chars);
    }

    public PlaceholderScanner() {
        this(new Characters());
    }

    /**
     * Scans the text and returns a list specifically of {@link IndexedRawPlaceholder}.
     *
     * @param text  The source text to analyze.
     * @param from  The starting index.
     * @param end   The ending index (-1 for end of string).
     * @param depth The recursion depth.
     * @return A list of identified tags.
     */
    @Override
    public List<IndexedRawPlaceholder> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawPlaceholder.class::isInstance)
                .map(IndexedRawPlaceholder.class::cast)
                .toList();
    }

    public List<IndexedRawPlaceholder> scan(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawPlaceholder.class::isInstance)
                .map(IndexedRawPlaceholder.class::cast)
                .toList();
    }

    public List<IndexedRawPlaceholder> scan(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawPlaceholder.class::isInstance)
                .map(IndexedRawPlaceholder.class::cast)
                .toList();
    }

    public List<IndexedRawPlaceholder> scan(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawPlaceholder.class::isInstance)
                .map(IndexedRawPlaceholder.class::cast)
                .toList();
    }

    public List<IndexedRawPlaceholder> scanMax(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawPlaceholder.class::isInstance)
                .map(IndexedRawPlaceholder.class::cast)
                .toList();
    }

    public List<IndexedRawPlaceholder> scanMax(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawPlaceholder.class::isInstance)
                .map(IndexedRawPlaceholder.class::cast)
                .toList();
    }

    public Optional<IndexedRawPlaceholder> findFirst(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawPlaceholder)
                .map(IndexedRawPlaceholder.class::cast);
    }

    public Optional<IndexedRawPlaceholder> findLast(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawPlaceholder)
                .map(IndexedRawPlaceholder.class::cast);
    }

    public Stream<IndexedRawPlaceholder> stream(String text) {
        return super.stream(text)
                .filter((component) -> component instanceof IndexedRawPlaceholder)
                .map(IndexedRawPlaceholder.class::cast);
    }

    @Override
    public IndexedRawPlaceholder create(String text, int start, int end) {
        CString cText = new CString(text.trim());
        String split = characters().getSeparateConditions();
        int splitIndex = cText.indexOfIgnoringStrings(split);


        if (splitIndex == -1) {
            return new IndexedRawPlaceholder(getTag(text, start, text.length()), null, start, end);
        }

        return new IndexedRawPlaceholder(getTag(text, start, splitIndex), getConditions(text, start, splitIndex), start, end);
    }

    private IndexedRawTag getTag(String text, int start, int splitIndex) {
        String tagStr = text.substring(characters().getOpenTag().length(), splitIndex).trim();
        tagStr = tagStr.substring(0, (tagStr.length() - characters().getCloseTag().length()));

        return TagScanner.INSTANCE.create(tagStr, start, (start + tagStr.length()));
    }

    private IndexedRawConditions getConditions(String text, int start, int splitIndex) {
        String conditionStr = text.substring(splitIndex + 1).trim();

        return new IndexedRawConditions(conditionStr, ConditionScanner.INSTANCE.scan(conditionStr), start, (start + conditionStr.length()));
    }
}
