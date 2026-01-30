package me.tr.trformatter.analysis.characters;

import java.util.HashMap;
import java.util.Map;

public record CharacterSet(Map<String, String> characters) {

    public CharacterSet() {
        this(new HashMap<>());
    }

    public void addChars(Map<String, String> characters) {
        this.characters.putAll(characters);
    }

    public void addChar(String name, String character) {
        this.characters.put(name, character);
    }

    public String getChar(String name) {
        return characters.get(name);
    }

    public String popChar(String name) {
        return this.characters.remove(name);
    }

    public boolean removeChar(String name) {
        return this.characters.remove(name) != null;
    }
}
