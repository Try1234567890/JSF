package me.tr.trformatter.registries;

import me.tr.trformatter.analysis.lexer.tokens.params.types.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class ParamsTypeRegistry extends Registry<Function<String, Boolean>, ParamType<?>> {
    private static ParamsTypeRegistry instance;
    private final Map<Function<String, Boolean>, ParamType<?>> types = new LinkedHashMap<>();
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
        types.put(PLACEHOLDER::isCorrectType, PLACEHOLDER);

        types.put(BOOLEAN::isCorrectType, BOOLEAN);
        types.put(BYTE::isCorrectType, BYTE);
        types.put(SHORT::isCorrectType, SHORT);
        types.put(INTEGER::isCorrectType, INTEGER);
        types.put(LONG::isCorrectType, LONG); // Is important long is the last one number, otherwise all numbers will be long.

        types.put(FLOAT::isCorrectType, FLOAT);
        types.put(DOUBLE::isCorrectType, DOUBLE); // Is important double is after float, otherwise all floats will be doubles.

        types.put(CHAR::isCorrectType, CHAR);
        types.put(STRING::isCorrectType, STRING); // Is important string is after the 'char type', otherwise chars will be string too.
    }

    public static ParamsTypeRegistry getInstance() {
        if (instance == null) {
            instance = new ParamsTypeRegistry();
        }
        return instance;
    }

    public ParamType<?> retrieve(String key) {

        for (Map.Entry<Function<String, Boolean>, ParamType<?>> entry : types.entrySet()) {
            if (entry.getKey().apply(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public static ParamType<?> get(String key) {
        return getInstance().retrieve(key);
    }

    @Override
    protected Map<Function<String, Boolean>, ParamType<?>> getRegistry() {
        return types;
    }
}
