package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawTag;

import java.util.List;

public class TagScanner extends GenericScanner {
    public static final TagScanner INSTANCE = new TagScanner();

    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenTag() : Characters.DEF_OPEN_TAG;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getCloseTag() : Characters.DEF_CLOSE_TAG;
    }


    public TagScanner(Characters chars) {
        super(OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public TagScanner() {
        this(new Characters());
    }

    public IndexedRawTag scanOne(String text, int from, int end) {
        List<IndexedRawTag> tags = scan(text, from, end, 1);
        return tags.isEmpty() ? null : tags.getFirst();
    }

    public IndexedRawTag scanOne(String text, int start) {
        return scanOne(text, start, -1);
    }

    public IndexedRawTag scanOne(String text) {
        return scanOne(text, -1, -1);
    }

    public List<IndexedRawTag> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawTag)
                .map(comp -> (IndexedRawTag) comp)
                .toList();
    }

    public List<IndexedRawTag> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    public List<IndexedRawTag> scan(String text, int start) {
        return scan(text, start, -1);
    }

    public List<IndexedRawTag> scan(String text) {
        return scan(text, -1, -1);
    }

    @Override
    public IndexedRawTag create(String text, int start, int end) {
        IndexedRawParams params = ParamsScanner.INSTANCE.scanParams(text);

        return new IndexedRawTag(text, params, start, end);
    }
}
