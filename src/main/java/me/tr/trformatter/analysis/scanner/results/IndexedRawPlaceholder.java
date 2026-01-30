package me.tr.trformatter.analysis.scanner.results;

public record IndexedRawPlaceholder(IndexedRawTag tag, IndexedRawConditions conditions) implements IndexedRawComponent {

    @Override
    public int start() {
        return tag.start();
    }

    @Override
    public int end() {
        return conditions.end();
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

