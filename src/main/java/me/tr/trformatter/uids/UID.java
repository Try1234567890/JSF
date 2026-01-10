package me.tr.trformatter.uids;

import me.tr.trformatter.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Unique Identifier (UID) for a system component.
 * <p>Each UID is defined by a primary name and an optional array aliases.
 * To maintain system integrity, this class enforces an <b>instance uniqueness</b>
 * constraint: only one instance of a specific UID name can exist at any given time.
 * </p>
 * <p>The structure of a {@code UID} consists of:</p>
 * <ul>
 * <li><b>Name:</b> The primary unique string used to identify the component.</li>
 * <li><b>Aliases:</b> An array of alternative names associated with the primary ID.</li>
 * </ul>
 * * <p><b>Note:</b> Attempting to instantiate a {@code UID} with a name that
 * is already registered in the internal cache will result in a
 * {@link DuplicateUIDException}.</p>
 */
public class UID {
    /*
     * Cache of all instanced UID names to prevent duplicates.
     */
    private final static List<String> cache = new ArrayList<>();
    private String name;
    private String[] aliases;

    /**
     * Create a new Unique Identifier instance.
     *
     * <p>Every UID instanced must be unique and
     * cannot be re-instanced, otherwise an {@link DuplicateUIDException}
     * will be thrown.
     * </p>
     *
     * @param name    The name to assign
     * @param aliases The aliases of the id; {@code They can be null or empty}.
     * @throws DuplicateUIDException if the UID already exists.
     * @throws NullPointerException  if the id is null.
     */
    public UID(String name, String... aliases) {
        if (Validator.isNull(name))
            throw new NullPointerException("ID cannot be null");

        if (exists(name)) {
            throw new DuplicateUIDException(
                    "Duplicate UID detected for: " + this + ". Please ensure the placeholder UID is unique. " +
                            "Suggestion: prefix it with your app name (e.g., \"TrFormatter-OsName\"). " +
                            "Note: This UID is for internal identification only and will not be used by the end user."
            );
        }

        this.name = name;
        this.aliases = aliases == null ? new String[0] : aliases;
        cache.add(name);
    }

    /**
     * Create a new Unique Identifier instance.
     *
     * <p>Every UID instanced must be unique and
     * cannot be re-instanced, otherwise an {@link DuplicateUIDException}
     * will be thrown.
     * </p>
     *
     * @param name The name to assign
     * @throws DuplicateUIDException if the UID already exists.
     * @throws NullPointerException  if the id is null.
     */
    public UID(String name) {
        this(name, new String[0]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    private boolean exists(String name) {
        return cache.contains(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof UID uid) {
            return getName().equals(uid.getName())
                    && Arrays.equals(getAliases(), uid.getAliases());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), Arrays.hashCode(getAliases()));
    }

    @Override
    public String toString() {
        return "UID#" + hashCode() + " -> Name: \"" + name + "\"; Aliases: " + Arrays.toString(getAliases());
    }
}
