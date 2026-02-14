package me.tr.trformatter.strings.cases;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum CaseType {
    SNAKE(Pattern.compile("^[a-z0-9]+(_[a-z0-9]+)+$"), SnakeCase::new),
    CAMEL(Pattern.compile("^[a-z][a-zA-Z0-9]*$"), CamelCase::new),
    PASCAL(Pattern.compile("^[A-Z][a-zA-Z0-9]*$"), PascalCase::new),
    TRAIN(Pattern.compile("^[A-Z][a-z0-9]*(-[A-Z][a-z0-9]*)*$"), TrainCase::new),
    DOT(Pattern.compile("^[a-z0-9]+(\\.[a-z0-9]+)+$"), DotCase::new),
    UNKNOWN(Pattern.compile(".*"), s -> new StringCase(s) { // Fallback
        @Override public List<String> toSpaceCase(String text) { return List.of(text); }
        @Override public StringCase fromSpaceCase(List<String> text) { return this; }
    });

    private final Pattern pattern;
    private final Function<String, StringCase> factory;

    CaseType(Pattern pattern, Function<String, StringCase> factory) {
        this.pattern = pattern;
        this.factory = factory;
    }

    public StringCase create(String word) {
        return factory.apply(word);
    }

    public static CaseType detect(String word) {
        return Stream.of(values())
                .filter(type -> type != UNKNOWN)
                .filter(type -> type.pattern.matcher(word).matches())
                .findFirst()
                .orElse(UNKNOWN);
    }
}