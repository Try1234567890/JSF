package me.tr.formatter.strings.format.formats;

public class DotCase extends SeparatorCase {

    public DotCase(String str) {
        super(str, ".");
    }

    public static DotCase of(String str) {
        return new DotCase(str);
    }

    public static DotCase of(String str, String separator) {
        return new DotCase(str).separator(separator);
    }

    @Override
    public DotCase newInstance(String text) {
        return new DotCase(text);
    }

    @Override
    public DotCase separator(String separator) {
        super.separator(separator);
        return this;
    }
}
