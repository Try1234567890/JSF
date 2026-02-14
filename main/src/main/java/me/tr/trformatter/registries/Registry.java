package me.tr.trformatter.registries;

import java.util.HashMap;
import java.util.Map;

public abstract class Registry<K, V> {
    private final Map<K, V> map = new HashMap<>();

    protected Map<K, V> getRegistry() {
        return map;
    }

    public V retrieve(K key) {
        return getRegistry().get(key);
    }

    public void register(K key, V value) {
        getRegistry().put(key, value);
    }

    public void unregister(K key) {
        getRegistry().remove(key);
    }

    public boolean has(K key) {
        return getRegistry().containsKey(key);
    }

    public void modify(K key, K newKey, V value) {
        unregister(key);
        register(newKey, value);
    }
}
