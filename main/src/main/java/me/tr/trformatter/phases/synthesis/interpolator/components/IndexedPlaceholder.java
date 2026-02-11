package me.tr.trformatter.phases.synthesis.interpolator.components;

public class IndexedPlaceholder implements IndexedComponent {
    private final int start;
    private final int end;
    private final String result;

    public IndexedPlaceholder(int start, int end, String result) {
        this.start = start;
        this.end = end;
        this.result = result;
    }

    @Override
    public int start() {
        return start;
    }

    @Override
    public int end() {
        return end;
    }

    @Override
    public String result() {
        return result;
    }

    @Override
    public String toString() {
        return "IndexedPlaceholder[" + result() + " | start=" + start() + ", end=" + end() + "]";
    }
}
