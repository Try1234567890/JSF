package me.tr.formatter.registries;

import me.tr.formatter.uids.UID;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Registry<T> {
    private final Map<UID, T> registry = new HashMap<>();

    protected Registry() {
    }

    /**
     * Add a value to the current registry.
     *
     * @param value The value to add into registry
     * @param id    The value ids.
     * @return true only if the value has been added to the registry, otherwise false.
     */
    public boolean add(UID id, T value) {
        if (registry.containsKey(id)) {
            return false;
        }
        registry.put(id, value);
        return true;
    }

    /**
     * Get the first value that has at last one of the provided ids.
     *
     * @param id The ids to search into registry.
     * @return the found value that can be null if nothing is found.
     */
    public @Nullable T get(UID id) {
        return registry.get(id);
    }

    /**
     * Get the first value that has at last one of the provided ids.
     *
     * @param id The ids to search into registry.
     * @return the found value that can be null if nothing is found.
     */
    public @Nullable T get(String id) {
        for (Map.Entry<UID, T> entry : registry.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Remove the first value that has at last one of the provided ids.
     *
     * @param id The value ids to remove from the registry.
     * @return true only if the value has been removed, otherwise false.
     */
    public boolean remove(UID id) {
        if (registry.containsKey(id)) {
            registry.remove(id);
            return true;
        }
        return false;
    }

    public Map<UID, T> getRegistry() {
        return registry;
    }

    public static PlaceholdersRegistry getPlaceholderRegistry() {
        return PlaceholdersRegistry.getInstance();
    }

    public static FunctionsRegistry getFunctionRegistry() {
        return FunctionsRegistry.getInstance();
    }

    public static TagsRegistry getTagsRegistry() {
        return TagsRegistry.getInstance();
    }
}
