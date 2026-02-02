package me.tr.trformatter.phases.analysis.scanner.components;

import java.util.List;

public interface ParameterizedIndexedRawComponent extends IndexedRawComponent {

    IndexedRawParams params();

    default List<IndexedRawParam> asList() {
        return params().params();
    }

    default boolean hasParams() {
        return params() != null && !asList().isEmpty();
    }
}
