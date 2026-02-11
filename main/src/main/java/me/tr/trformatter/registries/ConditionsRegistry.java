package me.tr.trformatter.registries;

import me.tr.trformatter.components.Condition;
import me.tr.trformatter.defaults.conditions.IfDate;
import me.tr.trformatter.defaults.conditions.IfTime;
import me.tr.trformatter.uids.DuplicateUIDException;
import me.tr.trformatter.uids.UID;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConditionsRegistry extends Registry<UID, Condition> {
    private static ConditionsRegistry instance;
    private final Map<UID, Condition> conditions = new HashMap<>();

    private ConditionsRegistry() {
        register(new IfTime());
        register(new IfDate());
    }

    public static ConditionsRegistry getInstance() {
        if (instance == null) {
            instance = new ConditionsRegistry();
        }
        return instance;
    }

    @Override
    public void register(UID key, Condition value) {
        if (has(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the condition UID is unique. Suggestion: prefix it with your app name (e.g., \"TrFormatter-IsLinux\").");
        }
        super.register(key, value);
    }

    public void register(Condition value) {
        UID key = value.getUID();
        if (has(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the condition UID is unique. Suggestion: prefix it with your app name (e.g., \"TrFormatter-IsLinux\").");
        }
        super.register(key, value);
    }

    public Condition retrieve(String key) {

        for (Map.Entry<UID, Condition> entry : conditions.entrySet()) {
            UID uid = entry.getKey();
            if (uid.getName().equals(key)
                    || Arrays.asList(uid.getAliases()).contains(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    protected Map<UID, Condition> getRegistry() {
        return conditions;
    }
}
