package me.tr.trformatter.phases.analysis.scanner.components;

import java.util.List;

public record IndexedRawConditions(String component, List<IndexedRawCondition> conditions, int start,
                                   int end) implements IndexedRawComponent {

    @Override
    public String toString() {
        return "IRawConditions[" + component() + " | s=" + start + ", e=" + end + "]";
    }
}
