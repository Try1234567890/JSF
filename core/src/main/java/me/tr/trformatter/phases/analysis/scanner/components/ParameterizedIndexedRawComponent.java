package me.tr.trformatter.phases.analysis.scanner.components;

import java.util.ArrayList;
import java.util.List;

public interface ParameterizedIndexedRawComponent extends IndexedRawComponent {

    IndexedRawParams params();

    default List<IndexedRawParam> asList() {
        return hasParams() ? params().params() : new ArrayList<>();
    }

    default boolean hasParams() {
        return params() != null;
    }
}
