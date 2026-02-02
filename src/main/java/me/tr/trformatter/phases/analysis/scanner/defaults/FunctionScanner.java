package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunction;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunctions;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;

import java.util.ArrayList;
import java.util.List;

public class FunctionScanner extends GenericScanner {
    public static final FunctionScanner INSTANCE = new FunctionScanner();

    private static String OPEN_DEL(Characters chars) {
        return chars != null ? chars.getOpenFunction() : Characters.DEF_CLOSE_FUNCTIONS;
    }

    private static String CLOSE_DEL(Characters chars) {
        return chars != null ? chars.getCloseFunction() : Characters.DEF_CLOSE_FUNCTIONS;
    }


    public FunctionScanner(Characters chars) {
        super(OPEN_DEL(chars), CLOSE_DEL(chars), chars);
    }

    public FunctionScanner() {
        this(new Characters());
    }

    public IndexedRawFunction scanOne(String text, int from, int end) {
        List<IndexedRawFunction> tags = scan(text, from, end, 1);
        return tags.isEmpty() ? null : tags.getFirst();
    }

    public IndexedRawFunction scanOne(String text, int start) {
        return scanOne(text, start, -1);
    }

    public IndexedRawFunction scanOne(String text) {
        return scanOne(text, -1, -1);
    }

    public IndexedRawFunctions scanMultiple(String text, int from, int to, int depth) {
        List<IndexedRawFunction> functions = super.scan(text, from, to, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawFunction)
                .map(comp -> (IndexedRawFunction) comp)
                .toList();

        if (functions.isEmpty()) {
            return new IndexedRawFunctions(text, new ArrayList<>(), 0, 0);
        }

        int start = functions.getFirst().start();
        int end = functions.getLast().end();

        return new IndexedRawFunctions(text, functions, start, end);
    }

    public IndexedRawFunctions scanMultiple(String text, int start, int depth) {
        return scanMultiple(text, start, -1, depth);
    }

    public IndexedRawFunctions scanMultiple(String text, int start) {
        return scanMultiple(text, start, -1);
    }

    public IndexedRawFunctions scanMultiple(String text) {
        return scanMultiple(text, -1, -1);
    }

    public List<IndexedRawFunction> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth)
                .stream()
                .filter(comp -> comp instanceof IndexedRawFunction)
                .map(comp -> (IndexedRawFunction) comp)
                .toList();
    }

    public List<IndexedRawFunction> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    public List<IndexedRawFunction> scan(String text, int start) {
        return scan(text, start, -1);
    }

    public List<IndexedRawFunction> scan(String text) {
        return scan(text, -1, -1);
    }

    @Override
    public IndexedRawFunction create(String text, int start, int end) {
        IndexedRawParams params = ParamsScanner.INSTANCE.scanParams(text);

        return new IndexedRawFunction(text, params, start, end);
    }
}
