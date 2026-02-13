package me.tr.trformatter.strings.cases.helper;

import me.tr.trformatter.strings.cases.*;

import java.util.Map;
import java.util.function.Function;

public class Instancers {
    private static final Map<Class<? extends StringCase>, Function<String, StringCase>> INSTANCERS = Map.ofEntries(
            Map.entry(SnakeCase.class, SnakeCase::of),
            Map.entry(CamelCase.class, CamelCase::of),
            Map.entry(PascalCase.class, PascalCase::of),
            Map.entry(TrainCase.class, TrainCase::of),
            Map.entry(DotCase.class, DotCase::of)
    );


    private Instancers() {

    }

    public static StringCase get(String word, Class<? extends StringCase> clazz) {
        Function<String, StringCase> function = INSTANCERS.get(clazz);
        if (function == null) {
            return null;
        }
        return function.apply(word);
    }
}
