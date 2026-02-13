package me.tr.trformatter.strings.cases;

import java.util.List;

public class CamelCase extends PascalCase {
    public CamelCase(String word) {
        super(word);
    }

    public static CamelCase of(String word) {
        return new CamelCase(word);
    }

    @Override
    public List<String> toSpaceCase(String text) {
        List<String> pascal = super.toSpaceCase(text);
        String word = pascal.removeFirst();
        char newChar = Character.toLowerCase(word.charAt(0));
        String newWord = newChar + word.substring(1);
        pascal.addFirst(newWord);
        return pascal;
    }

    @Override
    public CamelCase fromSpaceCase(List<String> text) {
        String pascal = super.fromSpaceCase(text).getWord();
        return new CamelCase(Character.toLowerCase(pascal.charAt(0)) + pascal.substring(1));
    }
}
