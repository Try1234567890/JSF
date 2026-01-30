package me.tr.trformatter.analysis.lexer.tokens.params.manager;

import me.tr.trformatter.Validator;
import me.tr.trformatter.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.analysis.lexer.tokens.params.PlaceholderParamToken;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParamsContainer {
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
     * @return The param value if found, otherwise {@code null}.
     * @throws NullPointerException if the param name is null.
     */
    public Object get(String name) {
        Validator.isNull(name, "Param name is null");
        return params.get(ParamToken.normalizeName(name));
    }

    /**
     * Retrieve the param value with the name.
     *
     * @param name The param name.
     * @param <T>  The return type.
     * @return The param value if found and the type is correct, otherwise {@code null}.
     * @throws NullPointerException if the param name is null.
     */
    @SuppressWarnings("unchecked")
    public <T> T getAs(String name, Class<T> type) {
        Validator.isNull(name, "Param name is null");
        Object value = params.get(ParamToken.normalizeName(name));
        if (value == null)
            return null;

        if (type.isAssignableFrom(value.getClass())) {
            return (T) value;
        }

        return null;
    }

    /**
     * Checks if the provided value is a PlaceholderParamToken
     *
     * @param value the value to checks for.
     * @return {@code true} if it is, otherwise {@code false}.
     */
    public boolean isParamToken(Object value) {
        return value instanceof PlaceholderParamToken;
    }


    /**
     * Checks if the value of the param with provided name is a PlaceholderParamToken
     *
     * @param name the param name.
     * @return {@code true} if it is, otherwise {@code false}.
     * @throws NullPointerException if the param name is null.
     */
    public boolean isParamToken(String name) {
        return get(name) instanceof PlaceholderParamToken;
    }

    /**
     * Retrieve the param value as Placeholder Param if it is.
     *
     * @param name The param name.
     * @return The param value as Placeholder Param
     * @throws NullPointerException     if the param name is null.
     * @throws IllegalArgumentException if the param value is not a Placeholder Param.
     */
    public PlaceholderParamToken getAsPlaceholderOrThrown(String name) {
        Object value = get(name);
        if (!isParamToken(value)) {
            throw new IllegalArgumentException("Params " + name + " is not a placeholder");
        }
        return (PlaceholderParamToken) value;
    }

    /**
     * Retrieve the param value as Placeholder Param if it is.
     *
     * @param name The param name.
     * @return The param value as Placeholder Param if it is, otherwise {@code null}.
     * @throws NullPointerException if the param name is null.
     */
    public PlaceholderParamToken getAsPlaceholder(String name) {
        Object value = get(name);
        return isParamToken(value) ? (PlaceholderParamToken) value : null;
    }


    // INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS
    // INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS
    // INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS | INDEXED PARAMS

    public boolean insert(int index, Object value) {
        Validator.isNull(index, "Param index is null or negative " + index);
        boolean contains = indexedParams.containsKey(index);

        String name = generateName(index);
        params.put(name, value);
        indexedParams.put(index, name);

        return contains;
    }

    public boolean has(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        return indexedParams.containsKey(index);
    }

    public boolean isPresent(int index) {
        return get(index) != null;
    }

    public Object pop(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        String name = indexedParams.get(index);
        if (name == null) return null;
        return params.remove(name);
    }

    public boolean remove(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        return pop(index) != null;
    }

    public Object get(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        String name = indexedParams.get(index);

        if (name == null) return null;

        Object val = params.get(name);

        if (val == null) {
            return forceGet(index);
        }

        return val;
    }

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

    public boolean isParamToken(int index) {
        Validator.isNull(index, "Param index is null or negative " + index);
        return get(index) instanceof PlaceholderParamToken;
    }

    public PlaceholderParamToken getAsPlaceholderOrThrown(int index) {
        Object value = get(index);
        if (!isParamToken(value)) {
            throw new IllegalArgumentException("Params at " + index + " is not a placeholder");
        }
        return (PlaceholderParamToken) value;
    }

    public PlaceholderParamToken getAsPlaceholder(int index) {
        Object value = get(index);
        return isParamToken(value) ? (PlaceholderParamToken) value : null;
    }


    /**
     * Retrieve the amount of params that this container has.
     *
     * @return the amount of params;
     */
    public int size() {
        return params.size();
    }


    private String generateName(int index) {
        UUID uuid = UUID.randomUUID();
        return index + "|" + uuid;
    }
}

















