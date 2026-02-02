package me.tr.trformatter.phases.analysis.lexer.tokens.params;

import me.tr.trformatter.phases.analysis.lexer.tokens.Token;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.types.ParamType;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.types.StringType;
import me.tr.trformatter.registries.ParamsTypeRegistry;
import me.tr.trformatter.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

public class ParamToken implements Token {
    public static final Set<Character> ILLEGAL_CHARS = Set.of(
            '|', '[', ']', '{', '}', '(', ')', '*',
            '&', '^', '%', '$', '#', '@', '!', '-', '+',
            '=', ';', ':', '"', '\'', ',', '.', '/', '?'
    );
    private static final Logger LOGGER = LoggerFactory.getLogger(ParamToken.class);
    private final String name;
    private final ParamType<?> type;
    private final Object value;

    public ParamToken(String name, ParamType<?> type, Object value) {
        this.name = validate(name);
        this.type = type;
        this.value = value;
    }

    public static ParamToken of(String name, String value) {
        ParamType<?> type = ParamsTypeRegistry.get(value);

        if (type == null) {
            LOGGER.warn("Type of param {} value {} is not recognized. Using string...", name, value);
            return new ParamToken(name, StringType.TYPE, value);
        }

        Object val = type.convert(value);
        return new ParamToken(name, type, val);
    }

    public String getName() {
        return name;
    }

    public ParamType<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public <T> Optional<T> getValueAs(Class<T> clazz) {
        Object val = getValue();
        if (val == null)
            return Optional.empty();

        if (val.getClass().isAssignableFrom(clazz)) {
            //noinspection unchecked
            return Optional.of((T) val);
        }

        return Optional.empty();
    }

    public <T> T getValueAsOrNull(Class<T> clazz) {
        return getValueAs(clazz).orElse(null);
    }

    private static String validate(String name) {
        String newName = normalizeName(name);

        if (Validator.isNull(newName)) {
            throw new IllegalArgumentException(name + " is not valid.");
        }

        return newName;
    }

    public static String normalizeName(String name) {
        if (name == null) return "";

        StringBuilder sb = new StringBuilder();
        for (char c : name.toCharArray()) {
            if (ILLEGAL_CHARS.contains(c)) {
                LOGGER.error("The param name \"{}\" contains illegal characters, removing it from final name...", name);
                continue;
            }
            sb.append(c);
        }

        String newName = sb.toString();

        if (!newName.equals(name)) {
            LOGGER.info("The final name for param \"{}\" is \"{}\"", name, newName);
        }

        return newName;
    }

    @Override
    public String asString() {
        return name + "=" + value;
    }

    @Override
    public String toString() {
        return asString();
    }
}
