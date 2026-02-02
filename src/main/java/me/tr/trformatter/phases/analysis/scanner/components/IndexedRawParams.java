package me.tr.trformatter.phases.analysis.scanner.components;

import java.util.List;

public record IndexedRawParams(List<IndexedRawParam> params, int start, int end) implements IndexedRawComponent {

    public int size() {
        return params().size();
    }

    @Override
    public String component() {
        return String.join(", ", params.stream().map(IndexedRawParam::toString).toList());
    }

    @Override
    public String toString() {
        return "IRawParams[" + component() + " | s=" + start + ", e=" + end + "]";
    }

}
