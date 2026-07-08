package me.tr.trformatter.color.exceptions;

public class InvalidColorException extends RuntimeException {

    public InvalidColorException() {
    }

    public InvalidColorException(String message) {
        super(message);
    }

    public InvalidColorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidColorException(Throwable cause) {
        super(cause);
    }

    public InvalidColorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
