package me.tr.trformatter.palceholders.tags;

import me.tr.trformatter.registries.TagsRegistry;

public class TagResolver {

    public static String resolve(String str) {
        return resolve(str, str);
    }

    public static String resolve(String str, String placeholder) {
        for (Tag tag : TagsRegistry.getInstance().values()) {
            tag.setStr(placeholder);
            str = tag.resolve(str, tag.getReplace());
        }
        return str;
    }


}
