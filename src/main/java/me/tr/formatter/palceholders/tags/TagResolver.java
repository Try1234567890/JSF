package me.tr.formatter.palceholders.tags;

import me.tr.formatter.registries.TagsRegistry;

public class TagResolver {

    public static String resolve(String str) {
        return resolve(str, str);
    }

    public static String resolve(String str, String placeholder) {
        for (Tag tag : TagsRegistry.getInstance().getRegistry().values()) {
            tag.setStr(placeholder);
            str = tag.resolve(str, tag.getReplace());
        }
        return str;
    }


}
