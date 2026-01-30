package me.tr.trformatter.analysis.scanner.defaults;

import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawParam;
import me.tr.trformatter.analysis.scanner.results.IndexedRawParams;
import me.tr.trformatter.strings.CString;

import java.util.ArrayList;
import java.util.List;

public class ParamsScanner extends GenericScanner {
    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenParams() : Characters.DEF_OPEN_PARAMS;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getCloseParams() : Characters.DEF_CLOSE_PARAMS;
    }


    public ParamsScanner(String text, Characters chars) {
        super(text, OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public ParamsScanner(String text) {
        this(text, new Characters());
    }

    public IndexedRawParams scanParams(int from, int end, int depth) {
        List<IndexedRawParams> result = super.scan(from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawParams)
                .map(comp -> (IndexedRawParams) comp)
                .toList();
        return result.isEmpty() ? null : result.getFirst();
    }

    public IndexedRawParams scanParams(int start, int depth) {
        List<IndexedRawParams> result = scan(start, -1, depth);
        return result.isEmpty() ? null : result.getFirst();
    }

    public IndexedRawParams scanParams(int start) {
        List<IndexedRawParams> result = scan(start, -1);
        return result.isEmpty() ? null : result.getFirst();
    }

    public IndexedRawParams scanParams() {
        List<IndexedRawParams> result = scan(0, -1);
        return result.isEmpty() ? null : result.getFirst();
    }

    public List<IndexedRawParams> scan(int from, int end, int depth) {
        return super.scan(from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawParams)
                .map(comp -> (IndexedRawParams) comp)
                .toList();
    }

    public List<IndexedRawParams> scan(int start, int depth) {
        return scan(start, -1, depth);
    }

    public List<IndexedRawParams> scan(int start) {
        return scan(start, -1);
    }

    public List<IndexedRawParams> scan() {
        return scan(-1, -1);
    }

    @Override
    public IndexedRawParams create(String text, int start, int end) {
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











