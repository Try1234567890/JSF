package me.tr.formatter.strings.format;

import me.tr.formatter.TrUtility;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class TextFormat {
    private String str;
    private String separator;
    private String result;

    public TextFormat(String str) {
        this.str = str;
    }

    public String toSpaceFormat() {
        if (getSeparator() == null || getSeparator().isEmpty()) {
            if (hasDefaultSeparator())
                return toSpaceFormatWithDefaultSeparator();
            return toSpaceFormatWithoutSeparator();
        } else {
            StringBuilder sb = new StringBuilder();
            char[] chars = getStr().toCharArray();
            List<Character> result = new ArrayList<>();
            String separator = getSeparator();
            char firstSeparatorChar = separator.charAt(0);
            int startSeparatorIndex = -1;


            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];

                if (startSeparatorIndex == -1
                        && ch == firstSeparatorChar) {
                    startSeparatorIndex = i;
                }

                if (startSeparatorIndex != -1) {
                    result.add(ch);
                    String tempSeparator = toString(result.subList(startSeparatorIndex, result.size()));
                    if (tempSeparator.equals(separator)) {
                        result = result.subList(0, startSeparatorIndex);
                        startSeparatorIndex = -1;
                        result.add(' ');
                    } else if (tempSeparator.length() >= separator.length())
                        startSeparatorIndex = -1;
                    continue;
                }

                result.add(ch);
            }
            return sb.append(toString(result)).toString();
        }
    }

    protected String toSpaceFormatWithDefaultSeparator() {
        StringBuilder sb = new StringBuilder();
        char[] chars = getStr().toCharArray();
        for (char ch : chars) {
            if (ch == getDefaultSeparator()) {
                sb.append(' ');
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }


    protected String toString(List<Character> list) {
        StringBuilder sb = new StringBuilder();

        for (Character ch : list) sb.append(ch);

        return sb.toString();
    }

    public String getSeparator() {
        return separator;
    }

    public String getStr() {
        return str;
    }

    public TextFormat separator(String separator) {
        this.separator = separator;
        return this;
    }

    public TextFormat str(String str) {
        this.str = str;
        return this;
    }

    protected TextFormat result(String result) {
        this.result = result;
        return this;
    }

    protected String getResult() {
        return result;
    }

    protected abstract String toSpaceFormatWithoutSeparator();

    protected abstract char getDefaultSeparator();

    protected abstract boolean hasDefaultSeparator();

    public abstract TextFormat newInstance(String text);

    public abstract FormatResult toCaseFrom(TextFormat format);

    public FormatResult toCaseFrom(Class<? extends TextFormat> format) {
        try {
            return toCaseFrom(TrUtility.instantiate(format, getStr()));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
