package me.tr.trformatter.analysis.scanner;


import me.tr.trformatter.analysis.scanner.results.IndexedRawComponent;

import java.util.List;

public interface Scanner {

    List<? extends IndexedRawComponent> scan(int start, int end, int depth);

    default List<? extends IndexedRawComponent> scan(int start, int depth) {
        return scan(start, -1, depth);
    }

    default List<? extends IndexedRawComponent> scan(int start) {
        return scan(start, -1);
    }

    default List<? extends IndexedRawComponent> scan() {
        return scan(-1, -1);
    }
}