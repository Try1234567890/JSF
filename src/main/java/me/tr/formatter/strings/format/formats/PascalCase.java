package me.tr.formatter.strings.format.formats;

import me.tr.formatter.strings.format.FormatResult;
import me.tr.formatter.strings.format.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class PascalCase extends TextFormat {


    public PascalCase(String str) {
        super(str);
        this.separator("");
    }

    public static PascalCase of(String str) {
        return new PascalCase(str);
    }

    public static PascalCase of(String str, String separator) {
        return new PascalCase(str).separator(separator);
    }

    @Override
    public FormatResult toCaseFrom(TextFormat format) {
        List<Character> result = new ArrayList<>();
        String space = format.toSpaceFormat();
        char[] chars = space.toCharArray();

        for (int i = 0; chars.length > i; i++) {
            char ch = chars[i];
            if (i == 0 && Character.isLowerCase(ch)) {
                result.add(Character.toUpperCase(ch));
                continue;
            }

            if (ch == ' ') {
                for (Character c : getSeparator().toCharArray())
                    result.add(c);
                ch = chars[++i];
                result.add(Character.toUpperCase(ch));
                continue;
            }

            result.add(ch);
        }

        result(toString(result));
        return new FormatResult(this);
    }

    @Override
    protected String toSpaceFormatWithoutSeparator() {
        return new CamelCase(getStr()).toSpaceFormatWithoutSeparator().trim();
    }

    @Override
    protected char getDefaultSeparator() {
        return 0;
    }

    @Override
    protected boolean hasDefaultSeparator() {
        return false;
    }

    @Override
    public PascalCase newInstance(String text) {
        return new PascalCase(text);
    }

    @Override
    public PascalCase separator(String separator) {
        super.separator(separator);
        return this;
    }
}
