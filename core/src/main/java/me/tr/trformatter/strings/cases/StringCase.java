package me.tr.trformatter.strings.cases;

import java.util.List;

public abstract class StringCase {
    private final String word;

    protected StringCase(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public abstract List<String> toSpaceCase(String text);

    public abstract StringCase fromSpaceCase(List<String> text);

    public String toCaseAsString(CaseType targetType) {
        List<String> parts = toSpaceCase(this.word);
        StringCase targetInstance = targetType.create("");
        return targetInstance.fromSpaceCase(parts).getWord();
    }

    public static StringCase of(String word) {
        return CaseType.detect(word).create(word);
    }
}