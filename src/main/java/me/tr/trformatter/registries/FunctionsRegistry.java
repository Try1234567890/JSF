package me.tr.trformatter.registries;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.uids.DuplicateUIDException;
import me.tr.trformatter.uids.UID;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FunctionsRegistry extends Registry<UID, Function> {
    private static FunctionsRegistry instance;
    private final Map<UID, Function> functions = new HashMap<>();

    private FunctionsRegistry() {

    }

    public static FunctionsRegistry getInstance() {
        if (instance == null) {
            instance = new FunctionsRegistry();
        }
        return instance;
    }

    @Override
    public void register(UID key, Function value) {
        if (has(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the function UID is unique. Suggestion: prefix it with your app name (e.g., \"formatter_lower_case\").");
        }
        super.register(key, value);
    }

    public void register(Function value) {
        UID key = value.getUID();
        if (has(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the function UID is unique. Suggestion: prefix it with your app name (e.g., \"formatter_lower_case\").");
        }
        super.register(key, value);
    }

    public Function retrieve(String key) {

        for (Map.Entry<UID, Function> entry : functions.entrySet()) {
            UID uid = entry.getKey();
            if (uid.getName().equals(key)
                    || Arrays.asList(uid.getAliases()).contains(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    protected Map<UID, Function> getRegistry() {
        return functions;
    }
}
