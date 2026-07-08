package me.tr.trformatter.registries;

import me.tr.trformatter.components.Condition;
import me.tr.trformatter.defaults.conditions.IfDate;
import me.tr.trformatter.defaults.conditions.IfTime;
import me.tr.trformatter.uids.DuplicateUIDException;
import me.tr.trformatter.uids.UID;
import me.tr.utilities.registries.Registry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConditionsRegistry extends Registry<UID, Condition> {
    private ConditionsRegistry() {
        register(new IfTime());
        register(new IfDate());
    }

    private record Holder() {
        private static final ConditionsRegistry INSTANCE = new ConditionsRegistry();
    }

    public static ConditionsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Condition register(UID key, Condition value) throws DuplicateUIDException {
        if (containsKey(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the condition UID is unique. Suggestion: prefix it with your app name (e.g., \"TrFormatter-IsLinux\").");
        }
        return super.register(key, value);
    }

    public Condition register(Condition value) throws DuplicateUIDException {
        UID key = value.getUID();
        if (containsKey(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the condition UID is unique. Suggestion: prefix it with your app name (e.g., \"TrFormatter-IsLinux\").");
        }
        return super.register(key, value);
    }

    public Optional<Condition> retrieve(String key) {

        for (Map.Entry<UID, Condition> entry : getInstance().internalMap.entrySet()) {
            UID uid = entry.getKey();
            if (uid.getName().equals(key)
                    || uid.getAliasesAsSet().contains(key)) {
                return Optional.ofNullable(entry.getValue());
            }
        }

        return Optional.empty();
    }
}
