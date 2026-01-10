package me.tr.trformatter.uids;

/**
 * This exception is thrown if an UID with the same
 * name as one already registered is detected.
 */
public class DuplicateUIDException extends RuntimeException {
    public DuplicateUIDException(String message) {
        super(message);
    }
}
