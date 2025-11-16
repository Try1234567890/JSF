package me.tr.trformatter.registries;

import me.tr.trformatter.palceholders.placeholders.Placeholder;

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
