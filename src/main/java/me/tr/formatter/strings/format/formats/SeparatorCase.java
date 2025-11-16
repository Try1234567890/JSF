package me.tr.formatter.strings.format.formats;

import me.tr.formatter.strings.format.FormatResult;
import me.tr.formatter.strings.format.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class SeparatorCase extends TextFormat {


    public SeparatorCase(String str, String separator) {
        super(str);
        super.separator(separator);
    }


    public static SeparatorCase of(String str, String separator) {
        return new SeparatorCase(str, separator);
    }

    @Override
    public FormatResult toCaseFrom(TextFormat format) {
        List<Character> result = new ArrayList<>();
        String space = format.toSpaceFormat();
        char[] chars = space.toCharArray();

        for (char ch : chars) {
            if (ch == ' ') {
                for (Character c : getSeparator().toCharArray())
                    result.add(c);
                continue;
            }

            result.add(ch);
        }

        result(toString(result));
        return new FormatResult(this);
    }

    @Override
    public SeparatorCase str(String str) {
        super.str(str);
        return this;
    }

    @Override
    public SeparatorCase separator(String separator) {
        super.separator(separator);
        return this;
    }

    @Override
    protected String toSpaceFormatWithoutSeparator() {
        return toSpaceFormatWithDefaultSeparator();
    }

    @Override
    public char getDefaultSeparator() {
        return '_';
    }

    @Override
    public boolean hasDefaultSeparator() {
        return true;
    }

    @Override
    public SeparatorCase newInstance(String text) {
        return new SeparatorCase(text, "_");
    }
}
