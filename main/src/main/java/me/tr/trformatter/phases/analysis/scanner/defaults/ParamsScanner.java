package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParam;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;
import me.tr.trformatter.strings.CString;
import me.tr.trformatter.utility.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ParamsScanner extends GenericScanner {
    public static final ParamsScanner INSTANCE = new ParamsScanner();

    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenParams() : Characters.DEF_OPEN_PARAMS;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getCloseParams() : Characters.DEF_CLOSE_PARAMS;
    }


    public ParamsScanner(Characters chars) {
        super(OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public ParamsScanner() {
        this(new Characters());
    }


    /**
     * Scans the text and returns a list specifically of {@link IndexedRawParams}.
     *
     * @param text  The source text to analyze.
     * @param from  The starting index.
     * @param end   The ending index (-1 for end of string).
     * @param depth The recursion depth.
     * @return A list of identified tags.
     */
    @Override
    public List<IndexedRawParam> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawParam> scan(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawParam> scan(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawParam> scan(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawParam> scanMax(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawParam> scanMax(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .findFirst()
                .orElse(new ArrayList<>());
    }


    public Optional<IndexedRawParams> scanParams(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawParams> scanParams(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawParams> scanParams(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawParams> scanParams(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawParams> scanMaxParams(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawParams> scanMaxParams(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst();
    }

    public IndexedRawParams scanParamsOrNull(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawParams scanParamsOrNull(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawParams scanParamsOrNull(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawParams scanParamsOrNull(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawParams scanMaxParamsOrNull(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawParams scanMaxParamsOrNull(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawParams.class::isInstance)
                .map(IndexedRawParams.class::cast)
                .findFirst()
                .orElse(null);
    }


    public Optional<IndexedRawParam> findFirst(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawParams)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .map(List::getFirst);
    }

    public Optional<IndexedRawParam> findLast(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawParams)
                .map(IndexedRawParams.class::cast)
                .map(IndexedRawParams::params)
                .map(List::getLast);
    }

    public IndexedRawParam findFirstONull(String text) {
        return findFirst(text).orElse(null);
    }

    public IndexedRawParam findLastOrNull(String text) {
        return findLast(text).orElse(null);
    }

    public Stream<IndexedRawParams> stream(String text) {
        return super.stream(text)
                .filter((component) -> component instanceof IndexedRawParams)
                .map(IndexedRawParams.class::cast);
    }

    @Override
    public IndexedRawParams create(String text, int start, int end) {
        if (Validator.isNull(text)) {
            return new IndexedRawParams(new ArrayList<>(), start, end);
        }

        CString cText = new CString(text);
        String split = characters().getSplitParams();

        List<IndexedRawParam> indexedRawParams = new ArrayList<>();
        int from;
        int nextDelim = -1;
        while ((nextDelim = cText.indexOfIgnoringStrings(split, from = nextDelim + 1)) != -1) {
            CString paramStr = new CString(cText.substring(from, nextDelim));
            IndexedRawParam param = create(paramStr, (start + from), (start + paramStr.length()));
            indexedRawParams.add(param);
        }

        if (from <= cText.length()) {
            CString paramStr = new CString(cText.substring(from));
            IndexedRawParam param = create(paramStr, (start + from), (start + paramStr.length()));
            indexedRawParams.add(param);
        }

        return new IndexedRawParams(indexedRawParams, start, end);
    }

    private IndexedRawParam create(CString paramStr, int start, int end) {
        String associate = characters().getAssociateParams();
        int associateIndex = paramStr.indexOfIgnoringStrings(associate);

        String name = paramStr.substring(0, associateIndex);
        String value = paramStr.substring(associateIndex + 1);

        return new IndexedRawParam(name, value, start, end);
    }
}











