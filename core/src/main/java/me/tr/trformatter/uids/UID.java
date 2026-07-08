package me.tr.trformatter.uids;

import me.tr.utilities.validators.Preconditions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * An UID (Unique Identifier) is a unique identifier for any components inside
 * the entire library, including any third-party components.
 * <p>
 * The UID is used to identify components in the library and to avoid
 * conflicts between different components.
 * The {@code name} and {@code aliases} are case-sensitive and the {@code name}
 * must be unique, instead the {@code aliases} can be null or empty.
 * If two components have the same {@code name}, a {@link DuplicateUIDException} is thrown.
 * <p>
 * The convention is to use the own application name as a prefix for own components
 * inside the {@code name} to prevent conflicts with others third-party components.
 */
public class UID {
    private final String name;
    private final String[] aliases;
    private final Set<String> aliasesSet;

    /**
     * Create a new Unique Identifier instance.
     *
     * @param name    The name to assign
     * @param aliases The aliases of the id; {@code They can be null or empty}.
     * @throws IllegalArgumentException if the {@code name} is null or empty.
     */
    public UID(String name, String... aliases) {
        Preconditions.parameterNotNull(name, "name");
        this.name = name;
        this.aliases = Preconditions.simpleNotNull(aliases, new String[0]);
        this.aliasesSet = new HashSet<>(Arrays.asList(aliases));
    }

    public static UID of(String name) {
        return new UID(name);
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public Set<String> getAliasesAsSet() {
        return aliasesSet;
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
        return "UID[Name: \"" + name + "\" | Aliases: " + String.join(", ", aliases) + "]";
    }
}
