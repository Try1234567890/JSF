package me.tr.trformatter.strings.cases;

import me.tr.trformatter.strings.cases.helper.Instancers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class StringCase {
    private static final Map<Class<? extends StringCase>, Pattern> CASE_PATTERNS = Map.ofEntries(
            Map.entry(SnakeCase.class, Pattern.compile("^[a-z0-9]+(_[a-z0-9]+)+$")),
            Map.entry(CamelCase.class, Pattern.compile("^[a-z][a-zA-Z0-9]*$")),
            Map.entry(PascalCase.class, Pattern.compile("^[A-Z][a-zA-Z0-9]*$")),
            Map.entry(TrainCase.class, Pattern.compile("^[A-Z][a-z0-9]*(-[A-Z][a-z0-9]*)*$")),
            Map.entry(DotCase.class, Pattern.compile("^[a-z0-9]+(\\.[a-z0-9]+)+$"))
    );
    private static final Map<Pattern, Class<? extends StringCase>> PATTERNS_CASE = Map.ofEntries(
            Map.entry(Pattern.compile("^[a-z0-9]+(_[a-z0-9]+)+$"), SnakeCase.class),
            Map.entry(Pattern.compile("^[a-z][a-zA-Z0-9]*$"), CamelCase.class),
            Map.entry(Pattern.compile("^[A-Z][a-zA-Z0-9]*$"), PascalCase.class),
            Map.entry(Pattern.compile("^[A-Z][a-z0-9]*(-[A-Z][a-z0-9]*)*$"), TrainCase.class),
            Map.entry(Pattern.compile("^[a-z0-9]+(\\.[a-z0-9]+)+$"), DotCase.class)
    );

    private final String word;
    private final Class<? extends StringCase> wordCase;

    protected StringCase(String word) {
        this.word = word;
        this.wordCase = getCase();
    }

    public static StringCase of(String word) {
        Class<? extends StringCase> cases = getCase(word);
        return Instancers.get(word, cases);
    }

    public String getWord() {
        return word;
    }

    public abstract List<String> toSpaceCase(String text);

    public abstract StringCase fromSpaceCase(List<String> text);

    public Class<? extends StringCase> getCase() {
        if (wordCase != null) {
            return wordCase;
        }
        return getCase(word);
    }

    public String toCaseAsString(Class<? extends StringCase> toCase) {
        StringCase cases = Instancers.get(getWord(), toCase);
        if (cases != null) {
            List<String> spaceCase = toSpaceCase(getWord());
            return cases.fromSpaceCase(spaceCase).getWord();
        }
        return "";
    }

    public boolean isSnakeCase() {
        return isSnakeCase(getWord());
    }

    public boolean isCamelCase() {
        return isCamelCase(getWord());
    }

    public boolean isPascalCase() {
        return isPascalCase(getWord());
    }

    public boolean isTrainCase() {
        return isTrainCase(getWord());
    }

    public boolean isDotCase() {
        return isDotCase(getWord());
    }

    public static boolean isSnakeCase(String word) {
        return CASE_PATTERNS.get(SnakeCase.class).matcher(word).matches();
    }

    public static boolean isCamelCase(String word) {
        return CASE_PATTERNS.get(CamelCase.class).matcher(word).matches();
    }

    public static boolean isPascalCase(String word) {
        return CASE_PATTERNS.get(PascalCase.class).matcher(word).matches();
    }

    public static boolean isTrainCase(String word) {
        return CASE_PATTERNS.get(TrainCase.class).matcher(word).matches();
    }

    public static boolean isDotCase(String word) {
        return CASE_PATTERNS.get(DotCase.class).matcher(word).matches();
    }

    public static Class<? extends StringCase> getCase(String word) {
        for (Map.Entry<Pattern, Class<? extends StringCase>> entry : PATTERNS_CASE.entrySet()) {
            if (entry.getKey().matcher(word).matches()) {
                return entry.getValue();
            }
        }

        return StringCase.class;
    }
}
