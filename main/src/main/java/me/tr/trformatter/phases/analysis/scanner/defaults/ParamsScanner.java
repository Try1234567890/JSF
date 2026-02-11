package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParam;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;
import me.tr.trformatter.strings.CString;
import me.tr.trformatter.utility.Validator;

import java.util.ArrayList;
import java.util.List;

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

    public IndexedRawParams scanParams(String text, int from, int end, int depth) {
        List<IndexedRawParams> result = super.scan(text, from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawParams)
                .map(comp -> (IndexedRawParams) comp)
                .toList();
        return result.isEmpty() ? null : result.getFirst();
    }

    public IndexedRawParams scanParams(String text, int start, int end) {
        List<IndexedRawParams> result = scan(text, start, end, -1);
        return result.isEmpty() ? null : result.getFirst();
    }

    public IndexedRawParams scanParams(String text, int start) {
        List<IndexedRawParams> result = scan(text, start, -1);
        return result.isEmpty() ? null : result.getFirst();
    }

    public IndexedRawParams scanParams(String text) {
        List<IndexedRawParams> result = scan(text, 0, -1);
        return result.isEmpty() ? null : result.getFirst();
    }

    public List<IndexedRawParams> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawParams)
                .map(comp -> (IndexedRawParams) comp)
                .toList();
    }

    public List<IndexedRawParams> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    public List<IndexedRawParams> scan(String text, int start) {
        return scan(text, start, -1);
    }

    public List<IndexedRawParams> scan(String text) {
        return scan(text, -1, -1);
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











