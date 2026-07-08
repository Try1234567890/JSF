package me.tr.trformatter.phases.analysis.scanner;

import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawComponent;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Interface for scanning and analyzing indexed components within strings.
 */
public interface Scanner {

    /**
     * Core method to scan for components within a specific range and depth.
     *
     * @param text  The input text to scan.
     * @param start The start index (inclusive). Use -1 for the beginning of the text.
     * @param end   The end index (exclusive). Use -1 for the end of the text.
     * @param depth The maximum nesting depth. Use -1 for unlimited depth.
     * @return A list of components extending {@link IndexedRawComponent}.
     */
    List<? extends IndexedRawComponent> scan(String text, int start, int end, int depth);

    default List<? extends IndexedRawComponent> scan(String text, int start, int end) {
        return scan(text, start, end, -1);
    }

    default List<? extends IndexedRawComponent> scan(String text, int start) {
        return scan(text, start, -1);
    }

    default List<? extends IndexedRawComponent> scan(String text) {
        return scan(text, 0);
    }

    default List<? extends IndexedRawComponent> scanMax(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    default List<? extends IndexedRawComponent> scanMax(String text, int depth) {
        return scan(text, 0, -1, depth);
    }

    default Optional<? extends IndexedRawComponent> findFirst(String text) {
        List<? extends IndexedRawComponent> components = scan(text, 0, -1, 1);
        return components.isEmpty() ? Optional.empty() : Optional.of(components.getFirst());
    }

    default Optional<? extends IndexedRawComponent> findLast(String text) {
        List<? extends IndexedRawComponent> components = scan(text);
        return components.isEmpty() ? Optional.empty() : Optional.of(components.getLast());
    }

    default Stream<? extends IndexedRawComponent> stream(String text) {
        return scan(text).stream();
    }

    default boolean containsAny(String text) {
        return !scan(text, 0, -1, 1).isEmpty();
    }

    default boolean isEmpty(String text) {
        return text == null || text.isBlank() || !containsAny(text);
    }

    default long count(String text) {
        return scan(text).size();
    }

    default boolean isFullMatch(String text) {
        if (text == null || text.isEmpty()) return false;
        List<? extends IndexedRawComponent> res = scan(text, 0, text.length(), 1);
        return res.size() == 1 && res.getFirst().start() == 0 && res.getFirst().end() == text.length();
    }

    default String stripComponents(String text) {
        return replaceComponents(text, c -> "");
    }

    default String replaceComponents(String text, Function<IndexedRawComponent, String> replacer) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder sb = new StringBuilder(text);
        List<? extends IndexedRawComponent> components = scan(text);

        // Iterate backwards to avoid index shifting
        for (int i = components.size() - 1; i >= 0; i--) {
            IndexedRawComponent c = components.get(i);
            sb.replace(c.start(), c.end(), replacer.apply(c));
        }
        return sb.toString();
    }

    default void forEach(String text, Consumer<? super IndexedRawComponent> action) {
        scan(text).forEach(action);
    }

    default Map<Class<?>, List<IndexedRawComponent>> groupByComponentType(String text) {
        return scan(text).stream().collect(Collectors.groupingBy(Object::getClass));
    }
}