package me.tr.trformatter.phases.analysis.scanner.chars;

public class Character implements Cloneable {
    private final String name;
    private String delimiter;

    Character(String name, String delimiter) {
        this.name = name;
        this.delimiter = delimiter;
    }

    public static Character get(String name) {
        return Characters.retrieve(name);
    }

    public static Character of(String name, String delimiter) {
        if (Characters.contains(name)) {
            return Characters.retrieve(name);
        } else {
            Character character = new Character(name, delimiter);
            Characters.add(name, character);
            return character;
        }
    }

    public String getName() {
        return name;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public Character setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    @Override
    public String toString() {
        return "Char[" + getName() + " -> \"" + getDelimiter() + "\"]";
    }

    @Override
    public Character clone() {
        try {
            Character clone = (Character) super.clone();
            clone.delimiter = delimiter;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
