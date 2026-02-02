package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawConditions;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawTag;
import me.tr.trformatter.strings.CString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlaceholderScanner extends GenericScanner {
    public static final PlaceholderScanner INSTANCE = new PlaceholderScanner();
    private final Logger LOGGER = LoggerFactory.getLogger(PlaceholderScanner.class);

    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenPlaceholder() : Characters.DEF_OPEN_PLACEHOLDER;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getClosePlaceholder() : Characters.DEF_CLOSE_PLACEHOLDER;
    }


    public PlaceholderScanner(Characters chars) {
        super(OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public PlaceholderScanner() {
        this(new Characters());
    }

    public IndexedRawPlaceholder scanOne(String text, int from, int end) {
        List<IndexedRawPlaceholder> placeholders = scan(text, from, end, 1);
        return placeholders.isEmpty() ? null : placeholders.getFirst();
    }

    public IndexedRawPlaceholder scanOne(String text, int start) {
        return scanOne(text, start, -1);
    }

    public IndexedRawPlaceholder scanOne(String text) {
        return scanOne(text, -1, -1);
    }

    public List<IndexedRawPlaceholder> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawPlaceholder)
                .map(comp -> (IndexedRawPlaceholder) comp)
                .toList();
    }

    public List<IndexedRawPlaceholder> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    public List<IndexedRawPlaceholder> scan(String text, int start) {
        return scan(text, start, -1);
    }

    public List<IndexedRawPlaceholder> scan(String text) {
        return scan(text, -1, -1);
    }

    @Override
    public IndexedRawPlaceholder create(String text, int start, int end) {
        CString cText = new CString(text.trim());
        String split = characters().getSplitComponents();
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
