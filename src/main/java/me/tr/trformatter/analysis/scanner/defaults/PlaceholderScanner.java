package me.tr.trformatter.analysis.scanner.defaults;

import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawConditions;
import me.tr.trformatter.analysis.scanner.results.IndexedRawParams;
import me.tr.trformatter.analysis.scanner.results.IndexedRawPlaceholder;
import me.tr.trformatter.analysis.scanner.results.IndexedRawTag;
import me.tr.trformatter.strings.CString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlaceholderScanner extends GenericScanner {
    private final Logger LOGGER = LoggerFactory.getLogger(PlaceholderScanner.class);

    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenPlaceholder() : Characters.DEF_OPEN_PLACEHOLDER;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getClosePlaceholder() : Characters.DEF_CLOSE_PLACEHOLDER;
    }


    public PlaceholderScanner(String text, Characters chars) {
        super(text, OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public PlaceholderScanner(String text) {
        this(text, new Characters());
    }

    public List<IndexedRawPlaceholder> scan(int from, int end, int depth) {
        return super.scan(from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawPlaceholder)
                .map(comp -> (IndexedRawPlaceholder) comp)
                .toList();
    }

    public List<IndexedRawPlaceholder> scan(int start, int depth) {
        return scan(start, -1, depth);
    }

    public List<IndexedRawPlaceholder> scan(int start) {
        return scan(start, -1);
    }

    public List<IndexedRawPlaceholder> scan() {
        return scan(-1, -1);
    }

    @Override
    public IndexedRawPlaceholder create(String text, int start, int end) {
        CString cText = new CString(text.trim());
        String split = characters().getSplitComponents();
        int splitIndex = cText.indexOfIgnoringStrings(split);


        if (splitIndex == -1) {
            LOGGER.debug("Index of {} is -1, parsing the tag only...", split);

            return new IndexedRawPlaceholder(getTag(text, start, text.length()), null);
        }

        LOGGER.debug("Index of {} is {}, parsing the tag and conditions", split, splitIndex);
        return new IndexedRawPlaceholder(getTag(text, start, splitIndex), getConditions(text, start, splitIndex));
    }

    private IndexedRawTag getTag(String text, int start, int splitIndex) {
        String tagStr = text.substring(characters().getOpenTag().length(), splitIndex).trim();
        tagStr = tagStr.substring(0, (tagStr.length() - characters().getCloseTag().length()));

        LOGGER.debug("Parsing params for tag {}...", tagStr);

        IndexedRawParams params = new ParamsScanner(tagStr).scanParams();

        LOGGER.debug("Parsed params for tag {} are {}", tagStr, params.size());

        int end = start + tagStr.length();
        return new IndexedRawTag(tagStr, params, start, end);
    }

    private IndexedRawConditions getConditions(String text, int start, int splitIndex) {
        String conditionStr = text.substring(splitIndex + 1).trim();
        return new IndexedRawConditions(conditionStr, new ConditionScanner(conditionStr).scan(), start, (start + text.length()));
    }
}
