package me.tr.trformatter.analysis.scanner.results;

public record IndexedRawCondition(String component, IndexedRawParams params, int start,
                                  int end) implements ParameterizedIndexedRawComponent {

    @Override
    public String toString() {
        return "IRawCondition[" + component() + " | params={" + params().component() + "} & s=" + start + ", e=" + end + "]";
    }
}
