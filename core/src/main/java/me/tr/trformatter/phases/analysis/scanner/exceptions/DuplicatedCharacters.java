package me.tr.trformatter.phases.analysis.scanner.exceptions;

import me.tr.trformatter.phases.analysis.scanner.chars.Character;

public class DuplicatedCharacters extends RuntimeException {
    public DuplicatedCharacters(String message) {
        super(message);
    }

    public DuplicatedCharacters(String input, Character alreadyUsedFor) {
        super("Duplicated character: " + input + ". Already used for " + alreadyUsedFor);
    }
}
