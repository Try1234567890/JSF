package me.tr.trformatter.uids;

/**
 * This exception is thrown if two component have the same UID name.
 */
public class DuplicateUIDException extends RuntimeException {
    public DuplicateUIDException(String message) {
        super(message);
    }
}
