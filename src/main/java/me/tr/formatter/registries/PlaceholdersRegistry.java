package me.tr.formatter.registries;

import me.tr.formatter.palceholders.placeholders.Placeholder;

public class PlaceholdersRegistry extends Registry<Placeholder> {
    private static PlaceholdersRegistry instance;

    public static PlaceholdersRegistry getInstance() {
        if (instance == null) {
            instance = new PlaceholdersRegistry();
        }
        return instance;
    }

    private PlaceholdersRegistry() {
    }

}
