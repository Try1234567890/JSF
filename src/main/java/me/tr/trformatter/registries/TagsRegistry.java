package me.tr.trformatter.registries;

import me.tr.trformatter.palceholders.tags.Tag;

public class TagsRegistry extends Registry<Tag> {
    private static TagsRegistry instance;

    public static TagsRegistry getInstance() {
        if (instance == null) {
            instance = new TagsRegistry();
        }
        return instance;
    }

    private TagsRegistry() {
    }

}
