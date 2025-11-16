package me.tr.formatter.registries;

import me.tr.formatter.palceholders.functions.Function;

public class FunctionsRegistry extends Registry<Function> {
    private static FunctionsRegistry instance;

    public static FunctionsRegistry getInstance() {
        if (instance == null) {
            instance = new FunctionsRegistry();
        }
        return instance;
    }

    private FunctionsRegistry() {
    }

}
