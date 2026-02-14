package me.tr.trformatter.phases.analysis.scanner.chars;

import me.tr.trformatter.registries.Registry;

import java.util.Collection;

public class Characters extends Registry<String, Character> {
    private Characters() {
    }

    private static Characters instance;

    public static Characters getInstance() {
        if (instance == null) {
            instance = new Characters();
        }
        return instance;
    }

    public static Character get(String key) {
        return getInstance().retrieve(key);
    }

    public static void add(Character value) {
        getInstance().register(value.getName(), value);
    }

    public static void add(String key, Character value) {
        getInstance().register(key, value);
    }

    public static void remove(String key) {
        getInstance().unregister(key);
    }

    public static boolean contains(String key) {
        return getInstance().has(key);
    }

    public static Collection<Character> all() {
        return getInstance().getRegistry().values();
    }
}
