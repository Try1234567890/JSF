package me.tr.trformatter.strings.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DotCase extends StringCase {
    protected DotCase(String word) {
        super(word);
    }

    public static DotCase of(String word) {
        return new DotCase(word);
    }

    @Override
    public List<String> toSpaceCase(String text) {
        String[] parts = text.split("\\.");
        return new ArrayList<>(Arrays.asList(parts));
    }

    @Override
    public DotCase fromSpaceCase(List<String> text) {
        StringBuilder sb = new StringBuilder();
        for (String part : text) {
            sb.append(part).append('.');
        }
        return new DotCase(sb.substring(0, (sb.toString().length() - 1)));
    }
}
