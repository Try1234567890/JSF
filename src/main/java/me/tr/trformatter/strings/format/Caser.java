package me.tr.trformatter.strings.format;

public class Caser {
    private final TextFormat textFormat;


    public Caser(TextFormat textFormat) {
        this.textFormat = textFormat;
    }

    public String toUpperCase() {
        return getResult().toUpperCase();
    }

    public String toLowerCase() {
        return getResult().toLowerCase();
    }

    public String invertCase() {
        StringBuilder result = new StringBuilder();
        for (char c : getResult().toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            } else result.append(Character.toUpperCase(c));
        }
        return result.toString();
    }

    public String alternateUpperCase() {
        StringBuilder result = new StringBuilder();
        char[] chars = getResult().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else result.append(Character.toLowerCase(c));
        }
        return result.toString();
    }

    public String alternateLowerCase() {
        StringBuilder result = new StringBuilder();
        char[] chars = getResult().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i % 2 != 0) {
                result.append(Character.toUpperCase(c));
            } else result.append(Character.toLowerCase(c));
        }
        return result.toString();
    }

    public String getResult() {
        return getTextFormat().getResult();
    }

    public TextFormat getTextFormat() {
        return textFormat;
    }
}
