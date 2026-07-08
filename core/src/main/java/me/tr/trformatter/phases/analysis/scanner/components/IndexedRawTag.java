package me.tr.trformatter.phases.analysis.scanner.components;

public record IndexedRawTag(String component, IndexedRawParams params, IndexedRawFunctions functions,
                            int start, int end) implements ParameterizedIndexedRawComponent {

    public boolean hasFunctions() {
        return functions != null && !functions.functions().isEmpty();
    }

    @Override
    public String toString() {
        return "IRawTag[" + component()  + (hasParams() ? " | params={" + params().component() + "}" : "") + (hasFunctions() ? " | functions={" + functions.component() + "}" : "") + " & s=" + start + ", e=" + end + "]";
    }
}
