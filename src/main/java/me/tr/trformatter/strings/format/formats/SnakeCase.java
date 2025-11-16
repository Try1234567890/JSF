package me.tr.trformatter.strings.format.formats;

public class SnakeCase extends SeparatorCase {

    public SnakeCase(String str) {
        super(str, "-");
    }

    public static SnakeCase of(String str) {
        return new SnakeCase(str);
    }

    public static SnakeCase of(String str, String separator) {
        return new SnakeCase(str).separator(separator);
    }

    @Override
    public SnakeCase newInstance(String text) {
        return new SnakeCase(text);
    }

    @Override
    public SnakeCase separator(String separator) {
        super.separator(separator);
        return this;
    }
}
