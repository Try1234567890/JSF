package me.tr.trformatter.phases.analysis.scanner.components;

import java.util.List;

public record IndexedRawFunctions(String component, List<IndexedRawFunction> functions, int start,
                                  int end) implements IndexedRawComponent {

    @Override
    public String toString() {
        return "IRawFunctions[" + component() + " | s=" + start + ", e=" + end + "]";
    }
}
