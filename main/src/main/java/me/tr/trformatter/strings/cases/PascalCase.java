package me.tr.trformatter.strings.cases;

import java.util.ArrayList;
import java.util.List;

public class PascalCase extends StringCase {
    protected PascalCase(String word) {
        super(word);
    }

    public static PascalCase of(String word) {
        return new PascalCase(word);
    }

    @Override
    public List<String> toSpaceCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                sb.append(" ").append(ch);
                continue;
            }
            sb.append(ch);
        }
        return new ArrayList<>(List.of(sb.toString().split(" ")));
    }

    @Override
    public PascalCase fromSpaceCase(List<String> text) {
        StringBuilder sb = new StringBuilder();
        for (String word : text) {
            char ch = word.charAt(0);

            sb.append(Character.toUpperCase(ch)).append(word.substring(1));
        }
        return new PascalCase(sb.toString());
    }
}
