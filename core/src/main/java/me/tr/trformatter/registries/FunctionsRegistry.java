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
import me.tr.utilities.registries.Registry;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class FunctionsRegistry extends Registry<UID, Function> {
    private FunctionsRegistry() {
        register(new ToLowerCase());
        register(new ToUpperCase());
        register(new IndexOf());
        register(new Replace());
        register(new SubString());
        register(new Split());
    }

    private record Holder() {
        private static final FunctionsRegistry INSTANCE = new FunctionsRegistry();
    }

    public static FunctionsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Function register(UID key, Function value) throws DuplicateUIDException {
        if (containsKey(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the function UID is unique. Suggestion: prefix it with your app name (e.g., \"formatter_lower_case\").");
        }
       return super.register(key, value);
    }

    public Function register(Function value) throws DuplicateUIDException {
        UID key = value.getUID();
        if (containsKey(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the function UID is unique. Suggestion: prefix it with your app name (e.g., \"formatter_lower_case\").");
        }
        return super.register(key, value);
    }

    public Optional<Function> retrieve(String key) {
        for (Map.Entry<UID, Function> entry : getInstance().internalMap.entrySet()) {
            UID uid = entry.getKey();
            if (uid.getName().equals(key)
                    || uid.getAliasesAsSet().contains(key)) {
                return Optional.ofNullable(entry.getValue());
            }
        }

        return Optional.empty();
    }
}
