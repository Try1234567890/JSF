package me.tr.trformatter.analysis.characters;

import java.util.HashMap;
import java.util.Map;

public class CharacterSetBuilder {
    private final Map<String, String> characters = new HashMap<>();

    public CharacterSetBuilder addChars(String name, String value) {
        characters.put(name, value);
        return this;
    }

    public CharacterSet build() {
        return new CharacterSet(characters);
    }
}
