package me.tr.trformatter.phases.analysis.scanner.components;

public record IndexedRawCondition(String component, IndexedRawParams params, int start,
                                  int end) implements ParameterizedIndexedRawComponent {

    @Override
    public String toString() {
        return "IRawCondition[" + component() + " | params={" + params().component() + "} & s=" + start + ", e=" + end + "]";
    }
}
