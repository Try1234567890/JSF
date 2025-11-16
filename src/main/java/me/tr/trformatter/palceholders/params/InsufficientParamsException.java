package me.tr.trformatter.palceholders.params;

public class InsufficientParamsException extends RuntimeException {
    public InsufficientParamsException(String message) {
        super(message);
    }
}
