package me.tr.trformatter.registries;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.defaults.functions.IndexOf;
import me.tr.trformatter.defaults.functions.Replace;
import me.tr.trformatter.defaults.functions.Split;
import me.tr.trformatter.defaults.functions.SubString;
import me.tr.trformatter.defaults.functions.cases.ToLowerCase;
import me.tr.trformatter.defaults.functions.cases.ToUpperCase;
import me.tr.trformatter.uids.DuplicateUIDException;
import me.tr.trformatter.uids.UID;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FunctionsRegistry extends Registry<UID, Function> {
    private static FunctionsRegistry instance;

    private FunctionsRegistry() {
        register(new ToLowerCase());
        register(new ToUpperCase());
        register(new IndexOf());
        register(new Replace());
        register(new SubString());
        register(new Split());
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

        for (Map.Entry<UID, Function> entry : getRegistry().entrySet()) {
            UID uid = entry.getKey();
            if (uid.getName().equals(key)
                    || Arrays.asList(uid.getAliases()).contains(key)) {
                return entry.getValue();
            }
        }

        return null;
    }
}
