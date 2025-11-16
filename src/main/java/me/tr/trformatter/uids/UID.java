package me.tr.trformatter.uids;

import java.util.*;

/**
 * This class represents a unique identifier (UID) for any object
 */
public class UID {
    // CACHE ALL UID INSTANCES PROPERTIES TO PREVENTS DUPLICATES
    private final static List<UID> cache = new ArrayList<>();
    private String id;
    private String[] aliases;

    public UID(String id, String... aliases) {
        if (exists(this)) {
            throw new DuplicateUIDException("Stopping initialization of " + id + "(" + String.join(", ", aliases) + ") because it already exists. Change it and re-run the program.");
        }
        this.id = id;
        this.aliases = aliases;
        cache.add(this);
    }

    public UID(String id) {
        this(id, new String[0]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    /**
     * Check if the provided UID already exists.
     *
     * @param uid the UID to check
     * @return {@code true} if the UID is found, otherwise {@code false}.
     */
    private static boolean exists(UID uid) {
        return cache.contains(uid);
    }

    @Override
    public String toString() {
        return "UID{id='" + id + '\'' + ", aliases=" + Arrays.toString(aliases) + '}';
    }
}
