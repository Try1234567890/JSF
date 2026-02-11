package me.tr.trformatter.phases.analysis.scanner;


import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawComponent;

import java.util.List;

public interface Scanner {

    List<? extends IndexedRawComponent> scan(String text, int start, int end, int depth);

    default List<? extends IndexedRawComponent> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    default List<? extends IndexedRawComponent> scan(String text, int start) {
        return scan(text, start, -1);
    }

    default List<? extends IndexedRawComponent> scan(String text) {
        return scan(text, -1, -1);
    }
}