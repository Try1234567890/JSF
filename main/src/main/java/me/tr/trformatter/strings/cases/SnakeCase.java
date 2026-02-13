package me.tr.trformatter.strings.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnakeCase extends StringCase {
    protected SnakeCase(String word) {
        super(word);
    }

    public static SnakeCase of(String word) {
        return new SnakeCase(word);
    }

    @Override
    public List<String> toSpaceCase(String text) {
        String[] parts = text.split("_");
        return new ArrayList<>(Arrays.asList(parts));
    }

    @Override
    public SnakeCase fromSpaceCase(List<String> text) {
        StringBuilder sb = new StringBuilder();
        for (String part : text) {
            sb.append(part).append('_');
        }
        return new SnakeCase(sb.substring(0, (sb.toString().length() - 1)));
    }
}
