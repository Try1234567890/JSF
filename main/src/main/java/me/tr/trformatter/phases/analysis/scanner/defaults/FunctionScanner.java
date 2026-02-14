package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunction;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunctions;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FunctionScanner extends GenericScanner {
    public static final FunctionScanner INSTANCE = new FunctionScanner();


    public FunctionScanner(Characters chars) {
        super(
                chars != null ? chars.getOpenFunction() : Characters.DEF_CLOSE_FUNCTION,
                chars != null ? chars.getCloseFunction() : Characters.DEF_CLOSE_FUNCTION,
                chars);
    }

    public FunctionScanner() {
        this(new Characters());
    }


    /**
     * Scans the text and returns a list specifically of {@link IndexedRawFunction}.
     *
     * @param text  The source text to analyze.
     * @param from  The starting index.
     * @param end   The ending index (-1 for end of string).
     * @param depth The recursion depth.
     * @return A list of identified tags.
     */
    @Override
    public List<IndexedRawFunction> scan(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawFunction> scan(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawFunction> scan(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawFunction> scan(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawFunction> scanMax(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public List<IndexedRawFunction> scanMax(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .findFirst()
                .orElse(new ArrayList<>());
    }


    public Optional<IndexedRawFunctions> scanFunctions(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawFunctions> scanFunctions(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawFunctions> scanFunctions(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawFunctions> scanFunctions(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawFunctions> scanMaxFunctions(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst();
    }

    public Optional<IndexedRawFunctions> scanMaxFunctions(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst();
    }

    public IndexedRawFunctions scanFunctionsOrNull(String text, int from, int end, int depth) {
        return super.scan(text, from, end, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawFunctions scanFunctionsOrNull(String text, int start, int end) {
        return super.scan(text, start, end).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawFunctions scanFunctionsOrNull(String text, int start) {
        return super.scan(text, start).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawFunctions scanFunctionsOrNull(String text) {
        return super.scan(text).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawFunctions scanMaxFunctionsOrNull(String text, int start, int depth) {
        return super.scanMax(text, start, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst()
                .orElse(null);
    }

    public IndexedRawFunctions scanMaxFunctionsOrNull(String text, int depth) {
        return super.scanMax(text, depth).stream()
                .filter(IndexedRawFunctions.class::isInstance)
                .map(IndexedRawFunctions.class::cast)
                .findFirst()
                .orElse(null);
    }

    public Optional<IndexedRawFunction> findFirst(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawFunction)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .map(List::getFirst);
    }

    public Optional<IndexedRawFunction> findLast(String text) {
        return super.findFirst(text)
                .filter((component) -> component instanceof IndexedRawFunction)
                .map(IndexedRawFunctions.class::cast)
                .map(IndexedRawFunctions::functions)
                .map(List::getLast);
    }

    public Stream<IndexedRawFunctions> stream(String text) {
        return super.stream(text)
                .filter((component) -> component instanceof IndexedRawFunction)
                .map(IndexedRawFunctions.class::cast);
    }

    @Override
    public IndexedRawFunction create(String text, int start, int end) {
        IndexedRawParams params = ParamsScanner.INSTANCE.scanParamsOrNull(text);

        return new IndexedRawFunction(text, params, start, end);
    }
}
