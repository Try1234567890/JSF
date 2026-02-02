package me.tr.trformatter.phases.analysis.scanner.components;

public record IndexedRawFunction(String component, IndexedRawParams params, int start,
                                 int end) implements ParameterizedIndexedRawComponent {

    @Override
    public String toString() {
        return "IRawFunction[" + component() + " | " + (hasParams() ? "params={" + params().component() + "} " : "") + " & s=" + start + ", e=" + end + "]";
    }
}
