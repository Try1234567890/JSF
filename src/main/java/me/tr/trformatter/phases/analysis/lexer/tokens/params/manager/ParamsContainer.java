package me.tr.trformatter.phases.analysis.lexer.tokens.params.manager;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters.FileGetter;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters.PathGetter;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters.ValueGetter;
import me.tr.trformatter.uids.UID;
import me.tr.trformatter.utility.Validator;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * A container class for managing and retrieving parameters by name, UID, or index.
 * It supports type-safe retrieval and specialized value getters for specific classes.
 */
public class ParamsContainer {
    private static final Map<Class<?>, ValueGetter<?>> GETTERS = Map.ofEntries(
            Map.entry(File.class, new FileGetter()),
            Map.entry(Path.class, new PathGetter())
    );
    private final Map<String, Object> params = new HashMap<>();
    private final Map<Integer, String> indexedParams = new HashMap<>();

    /**
     * Insert a new parameter to this instance.
     *
     * @param name  The parameter name
     * @param value The parameter value, can be {@code null}.
     * @return {@code true} if the params replaced another one, otherwise {@code false} if the params has been added.
     * @throws NullPointerException if param name is {@code null}.
     */
    public boolean insert(String name, Object value) {
        Validator.isNull(name, "Param name is null");
        boolean contains = params.containsKey(ParamToken.normalizeName(name));
        params.put(name, value);
        return contains;
    }

    /**
     * Insert a new parameter to this instance.
     *
     * @param token The param token to insert
     * @return {@code true} if the params replaced another one, otherwise {@code false} if the params has been added.
     * @throws NullPointerException if param token is {@code null}.
     */
    public boolean insert(ParamToken token) {
        Validator.isNull(token, "Token is null");
        return insert(token.getName(), token.getValue());
    }

    /**
     * Checks if the param is present.
     *
     * @param name The param name
     * @return {@code true} if is present, otherwise {@code false}.
     * @throws NullPointerException if the param name is null.
     */
    public boolean has(String name) {
        Validator.isNull(name, "Param name is null");
        return params.containsKey(ParamToken.normalizeName(name));
    }

    /**
     * Checks if the param is present and is {@code not null}
     *
     * @param name The param name
     * @return {@code true} if is present and {@code not null}, otherwise {@code false}.
     * @throws NullPointerException if the param name is null.
     */
    public boolean isPresent(String name) {
        if (!has(name)) return false;
        Object value = params.get(ParamToken.normalizeName(name));
        return value != null;
    }

    /**
     * Remove the param with the provided name if found.
     *
     * @param name The param name to remove.
     * @return The param value if found, otherwise {@code null}.
     * @throws NullPointerException if the param name is null.
     */
    public Object pop(String name) {
        Validator.isNull(name, "Param name is null");
        return params.remove(ParamToken.normalizeName(name));
    }

    /**
     * Remove the param with the provided name if found.
     *
     * @param name The param name to remove.
     * @return {@code true} if the param is found, otherwise {@code false}.
     * @throws NullPointerException if the param name is null.
     */
    public boolean remove(String name) {
        Validator.isNull(name, "Param name is null");
        return pop(name) != null;
    }

    /**
     * Retrieve the param value with the name.
     *
     * @param name The param name.
     * @return An {@link Optional} containing the param value if found, otherwise an empty {@link Optional}.
     * @throws NullPointerException if the param name is null.
     */
    public Optional<Object> get(String name) {
        Validator.isNull(name, "Param name is null");
        return Optional.ofNullable(params.get(ParamToken.normalizeName(name)));
    }

    /**
     * Retrieve the param value with the name, or null if not found.
     *
     * @param name The param name.
     * @return The param value if found, otherwise {@code null}.
     */
    public Object getOrNull(String name) {
        return get(name).orElse(null);
    }

    /**
     * Retrieve the param value using a {@link UID}, checking both the primary name and aliases.
     *
     * @param name The UID containing the name and aliases.
     * @return An {@link Optional} containing the param value if found, otherwise an empty {@link Optional}.
     * @throws NullPointerException if the UID is null.
     */
    public Optional<Object> get(UID name) {
        Validator.isNull(name, "Param name is null");
        Object value = getOrNull(name.getName());

        if (value != null) {
            return Optional.of(value);
        }

        int i = 0;
        String[] aliases = name.getAliases();
        while (i < aliases.length && (value = getOrNull(aliases[i])) == null) {
            i++;
        }

        return Optional.ofNullable(value);
    }

    /**
     * Retrieve the param value with the UID, or null if not found.
     *
     * @param name The UID identifier.
     * @return The param value if found, otherwise {@code null}.
     */
    public Object getOrNull(UID name) {
        return get(name).orElse(null);
    }

    /**
     * Retrieve the param value and attempt to cast it to the specified type,
     * using registered getters if available.
     *
     * @param name The param name.
     * @param type The class type to cast to.
     * @param <T>  The return type.
     * @return An {@link Optional} containing the typed value if successful.
     * @throws NullPointerException if the param name is null.
     */
    public <T> Optional<T> getAs(String name, Class<T> type) {
        Validator.isNull(name, "Param name is null");

        Optional<T> result = getAsFromGetters(name, type);

        if (result.isPresent()) {
            return result;
        }

        return getAsIgnoreGetters(name, type);
    }

    /**
     * Retrieve the param value via UID and attempt to cast it to the specified type,
     * using registered getters if available.
     *
     * @param name The UID identifier.
     * @param type The class type to cast to.
     * @param <T>  The return type.
     * @return An {@link Optional} containing the typed value if successful.
     * @throws NullPointerException if the UID is null.
     */
    public <T> Optional<T> getAs(UID name, Class<T> type) {
        Validator.isNull(name, "Param name is null");

        Optional<T> result = getAsFromGetters(name, type);

        if (result.isPresent()) {
            return result;
        }

        return getAsIgnoreGetters(name, type);
    }

    /**
     * Retrieve the param value cast to a type, or null if not found or incompatible.
     *
     * @param name The param name.
     * @param type The target class type.
     * @param <T>  The return type.
     * @return The typed value or {@code null}.
     */
    public <T> T getAsOrNull(String name, Class<T> type) {
        return getAs(name, type).orElse(null);
    }

    /**
     * Retrieve the param value via UID cast to a type, or null if not found or incompatible.
     *
     * @param name The UID identifier.
     * @param type The target class type.
     * @param <T>  The return type.
     * @return The typed value or {@code null}.
     */
    public <T> T getAsOrNull(UID name, Class<T> type) {
        return getAs(name, type).orElse(null);
    }

    /**
     * Retrieve the param value by name ignoring registered getters, only checking for type compatibility.
     *
     * @param name The param name.
     * @param type The target class type.
     * @param <T>  The return type.
     * @return An {@link Optional} containing the typed value.
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getAsIgnoreGetters(String name, Class<T> type) {
        Object value = getOrNull(name);

        if (value == null) {
            return Optional.empty();
        }

        if (type.isAssignableFrom(value.getClass())) {
            return Optional.of((T) value);
        }

        return Optional.empty();
    }

    /**
     * Retrieve the param value by UID ignoring registered getters, only checking for type compatibility.
     *
     * @param name The UID identifier.
     * @param type The target class type.
     * @param <T>  The return type.
     * @return An {@link Optional} containing the typed value.
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getAsIgnoreGetters(UID name, Class<T> type) {
        Object value = getOrNull(name);

        if (value == null) {
            return Optional.empty();
        }

        if (type.isAssignableFrom(value.getClass())) {
            return Optional.of((T) value);
        }

        return Optional.empty();
    }

    /**
     * Retrieve the param value by name ignoring getters, or null if incompatible.
     *
     * @param name The param name.
     * @param type The target class type.
     * @return The typed value or {@code null}.
     */
    public <T> T getAsIgnoreGettersOrNull(String name, Class<T> type) {
        return getAsIgnoreGetters(name, type).orElse(null);
    }

    /**
     * Retrieve the param value by UID ignoring getters, or null if incompatible.
     *
     * @param name The UID identifier.
     * @param type The target class type.
     * @return The typed value or {@code null}.
     */
    public <T> T getAsIgnoreGettersOrNull(UID name, Class<T> type) {
        return getAsIgnoreGetters(name, type).orElse(null);
    }

    /**
     * Retrieve the param value by name using registered specialized getters.
     *
     * @param name The param name.
     * @param type The target class type for which a getter might be registered.
     * @param <T>  The return type.
     * @return An {@link Optional} containing the result from the getter.
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getAsFromGetters(String name, Class<T> type) {
        ValueGetter<?> getter = GETTERS.get(type);

        if (getter != null) {
            return Optional.ofNullable((T) getter.get(this, name));
        }

        return Optional.empty();
    }

    /**
     * Retrieve the param value by UID using registered specialized getters.
     *
     * @param name The UID identifier.
     * @param type The target class type.
     * @param <T>  The return type.
     * @return An {@link Optional} containing the result from the getter.
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getAsFromGetters(UID name, Class<T> type) {
        ValueGetter<?> getter = GETTERS.get(type);

        if (getter != null) {
            return Optional.ofNullable((T) getter.get(this, name));
        }

        return Optional.empty();
    }

    /**
     * Retrieve the param value by name using getters, or null if none found.
     *
     * @param name The param name.
     * @param type The target class type.
     * @return The typed value or {@code null}.
     */
    public <T> T getAsFromGettersOrNull(String name, Class<T> type) {
        return getAsFromGetters(name, type).orElse(null);
    }

    /**
     * Retrieve the param value by UID using getters, or null if none found.
     *
     * @param name The UID identifier.
     * @param type The target class type.
     * @return The typed value or {@code null}.
     */
    public <T> T getAsFromGettersOrNull(UID name, Class<T> type) {
        return getAsFromGetters(name, type).orElse(null);
    }

    // INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS

    /**
     * Insert a value at a specific numeric index.
     *
     * @param index The param index.
     * @param value The value to insert.
     * @return {@code true} if an existing index was replaced, {@code false} otherwise.
     */
    public boolean insert(int index, Object value) {
        Validator.isNull(index, "Param index is null or negative " + index);
        boolean contains = indexedParams.containsKey(index);

        String name = generateName(index);
        params.put(name, value);
        indexedParams.put(index, name);

        return contains;
    }

    /**
     * Checks if a parameter exists at the specified index.
     *
     * @param index The param index.
     * @return {@code true} if present.
     */
    public boolean has(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        return indexedParams.containsKey(index);
    }

    /**
     * Checks if a parameter exists at the index and is not null.
     *
     * @param index The param index.
     * @return {@code true} if present and not null.
     */
    public boolean isPresent(int index) {
        return getOrNull(index) != null;
    }

    /**
     * Removes and returns the value at the specified index.
     *
     * @param index The param index.
     * @return The removed value or {@code null}.
     */
    public Object pop(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        String name = indexedParams.get(index);
        if (name == null) return null;
        return params.remove(name);
    }

    /**
     * Removes the value at the specified index.
     *
     * @param index The param index.
     * @return {@code true} if a value was removed.
     */
    public boolean remove(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        return pop(index) != null;
    }

    /**
     * Retrieve the value at the specified index, or null if not found.
     *
     * @param index The param index.
     * @return The value or {@code null}.
     */
    public Object getOrNull(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        String name = indexedParams.get(index);

        if (name == null) return null;

        Object val = params.get(name);

        if (val == null) {
            return forceGet(index);
        }

        return val;
    }

    /**
     * Scans all parameters to find a value whose key matches the pattern "index|uuid".
     *
     * @param index The index to search for.
     * @return The value if a matching key is found, otherwise {@code null}.
     */
    private Object forceGet(int index) {

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String name = entry.getKey();

            String[] parts = name.split("\\|");

            if (parts.length < 2) continue;

            String foundIndexStr = parts[0];

            try {
                int foundIndex = Integer.parseInt(foundIndexStr);
                if (index == foundIndex) {
                    return entry.getValue();
                }
            } catch (NumberFormatException ignored) {
            }


        }

        return null;
    }

    /**
     * Retrieve the amount of params that this container has.
     *
     * @return the amount of params;
     */
    public int size() {
        return params.size();
    }


    /**
     * Generates a unique internal name for an indexed parameter.
     *
     * @param index The param index.
     * @return A string in the format "index|uuid".
     */
    private String generateName(int index) {
        UUID uuid = UUID.randomUUID();
        return index + "|" + uuid;
    }
}