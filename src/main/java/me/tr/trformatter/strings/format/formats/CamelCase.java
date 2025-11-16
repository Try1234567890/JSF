package me.tr.trformatter.strings.format.formats;

import me.tr.trformatter.strings.format.FormatResult;
import me.tr.trformatter.strings.format.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class CamelCase extends TextFormat {

    public CamelCase(String str) {
        super(str);
        this.separator("");
    }

    public static CamelCase of(String str) {
        return new CamelCase(str);
    }

    public static CamelCase of(String str, String separator) {
        return new CamelCase(str).separator(separator);
    }

    @Override
    public FormatResult toCaseFrom(TextFormat format) {
        List<Character> result = new ArrayList<>();
        String space = format.toSpaceFormat();
        char[] chars = space.toCharArray();

        for (int i = 0; chars.length > i; i++) {
            char ch = chars[i];
            if (i == 0 && Character.isUpperCase(ch)) {
                result.add(Character.toLowerCase(ch));
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
        StringBuilder sb = new StringBuilder();
        char[] chars = getStr().toCharArray();
        for (char ch : chars) {
            if (Character.isUpperCase(ch)) {
                sb.append(' ').append(ch);
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public CamelCase str(String str) {
        super.str(str);
        return this;
    }

    public CamelCase separator(String separator) {
        super.separator(separator);
        return this;
    }

    @Override
    public char getDefaultSeparator() {
        return 0;
    }

    @Override
    public boolean hasDefaultSeparator() {
        return false;
    }

    @Override
    public CamelCase newInstance(String text) {
        return new CamelCase(text);
    }
}
