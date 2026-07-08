package me.tr.trformatter.phases.analysis.scanner.chars;

import me.tr.utilities.registries.Registry;

import java.util.Collection;

public class Characters extends Registry<String, Character> {
    private Characters() {
    }

    private record Holder() {
        private static final Characters INSTANCE = new Characters();
    }

    public static Characters getInstance() {
        return Holder.INSTANCE;
    }

    public static Character retrieve(String key) {
        return getInstance().get(key);
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
        return getInstance().containsKey(key);
    }

    public static Collection<Character> all() {
        return getInstance().values();
    }
}
