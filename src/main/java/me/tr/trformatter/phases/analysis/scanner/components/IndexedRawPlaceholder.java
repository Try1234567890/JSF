package me.tr.trformatter.phases.analysis.scanner.components;

public record IndexedRawPlaceholder(IndexedRawTag tag, IndexedRawConditions conditions, int start, int end) implements IndexedRawComponent {

    @Override
    public int start() {
        return start;
    }

    @Override
    public int end() {
        return end;
    }

    public boolean hasConditions() {
        return conditions != null && !conditions.conditions().isEmpty();
    }

    @Override
    public String component() {
        return tag.component() + (hasConditions() ? conditions.component() : "");
    }

    @Override
    public String toString() {
        return "IRawPlaceholder[" + tag.toString() + (hasConditions() ? " ^ " + conditions.toString() : "") + "]";
    }
}

