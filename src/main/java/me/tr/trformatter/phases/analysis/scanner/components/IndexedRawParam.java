package me.tr.trformatter.phases.analysis.scanner.components;

public record IndexedRawParam(String name, String value, int start, int end) implements IndexedRawComponent {

    @Override
    public String component() {
        return name + "=" + value;
    }

    @Override
    public String toString() {
        return "IRawParam[" + component() + " | s=" + start + ", e=" + end + "]";
    }
}
