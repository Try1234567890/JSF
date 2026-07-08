package me.tr.trformatter.registries;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.types.*;
import me.tr.utilities.registries.Registry;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ParamsTypeRegistry extends Registry<Function<String, Boolean>, ParamType<?>> {
    private static final PlaceholderTokenType PLACEHOLDER = new PlaceholderTokenType();
    private static final BooleanType BOOLEAN = new BooleanType();
    private static final ByteType BYTE = new ByteType();
    private static final CharType CHAR = new CharType();
    private static final DoubleType DOUBLE = new DoubleType();
    private static final FloatType FLOAT = new FloatType();
    private static final IntegerType INTEGER = new IntegerType();
    private static final LongType LONG = new LongType();
    private static final ShortType SHORT = new ShortType();
    private static final StringType STRING = new StringType();

    private ParamsTypeRegistry() {
        register(PLACEHOLDER::isCorrectType, PLACEHOLDER);

        register(BOOLEAN::isCorrectType, BOOLEAN);
        register(BYTE::isCorrectType, BYTE);
        register(SHORT::isCorrectType, SHORT);
        register(INTEGER::isCorrectType, INTEGER);
        register(LONG::isCorrectType, LONG); // Is important long is the last one number, otherwise all numbers will be long.

        register(FLOAT::isCorrectType, FLOAT);
        register(DOUBLE::isCorrectType, DOUBLE); // Is important double is after float, otherwise all floats will be doubles.

        register(CHAR::isCorrectType, CHAR);
        register(STRING::isCorrectType, STRING); // Is important string is after the 'char type', otherwise chars will be string too.

    }

    private record Holder() {
        private static final ParamsTypeRegistry INSTANCE = new ParamsTypeRegistry();
    }

    public static ParamsTypeRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<ParamType<?>> retrieve(String key) {

        for (Map.Entry<Function<String, Boolean>, ParamType<?>> entry : getInstance().internalMap.entrySet()) {
            if (entry.getKey().apply(key)) {
                return Optional.ofNullable(entry.getValue());
            }
        }

        return Optional.empty();
    }

    public static Optional<ParamType<?>> get(String key) {
        return getInstance().retrieve(key);
    }

}
