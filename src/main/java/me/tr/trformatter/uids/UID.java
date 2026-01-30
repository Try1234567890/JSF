package me.tr.trformatter.uids;

import me.tr.trformatter.Validator;

import java.util.*;

/**
 * Represents a Unique Identifier (UID) for a system component.
 * <p>Each UID is defined by a primary name and an optional array aliases.</p>
 * <p>The structure of a {@code UID} consists of:</p>
 * <ul>
 * <li><b>Name:</b> The primary unique string used to identify the component.</li>
 * <li><b>Aliases:</b> An array of alternative names associated with the primary ID.</li>
 */
public class UID {
    private String name;
    private String[] aliases;

    /**
     * Create a new Unique Identifier instance.
     *
     * @param name    The name to assign
     * @param aliases The aliases of the id; {@code They can be null or empty}.
     * @throws NullPointerException  if the id is null.
     */
    public UID(String name, String... aliases) {
        if (Validator.isNull(name))
            throw new NullPointerException("ID cannot be null");



        this.name = name;
        this.aliases = aliases == null ? new String[0] : aliases;
    }

    /**
     * Create a new Unique Identifier instance.
     *
     * @param name The name to assign
     * @throws NullPointerException  if the id is null.
     */
    public UID(String name) {
        this(name, new String[0]);
    }

    public static UID of(String name) {
        return new UID(name);
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
