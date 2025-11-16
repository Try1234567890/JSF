package me.tr.trformatter.palceholders.placeholders;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.tags.TagResolver;

import java.util.List;

public class PlaceholderResolver {
    private final List<Placeholder> placeholders;
    private final String str;


    protected PlaceholderResolver(List<Placeholder> placeholders, String str) {
        this.placeholders = placeholders;
        this.str = str;
    }

    public List<Placeholder> getPlaceholders() {
        return placeholders;
    }

    public String getStr() {
        return str;
    }

    public String resolve() {
        StringBuilder sb = new StringBuilder(this.str);
        for (int i = placeholders.size() - 1; i >= 0; i--) {
            final Placeholder placeholder = placeholders.get(i);
            String result = placeholder.process(this.str);
            Function[] functions = placeholder.getFunctions();
            for (Function function : functions) {
                result = function.process(result);
            }
            sb.replace(placeholder.getStart(), placeholder.getEnd(), result);
        }
        return TagResolver.resolve(sb.toString());
    }
}