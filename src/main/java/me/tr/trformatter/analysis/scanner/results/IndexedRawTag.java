package me.tr.trformatter.analysis.scanner.results;

public record IndexedRawTag(String component, IndexedRawParams params, int start,
                            int end) implements ParameterizedIndexedRawComponent {


    @Override
    public String toString() {
        return "IRawTag[" + component + " | params={" + params.component() + "} & s=" + start + ", e=" + end + "]";
    }
}
