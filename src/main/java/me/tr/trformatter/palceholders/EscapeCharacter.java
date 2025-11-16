package me.tr.trformatter.palceholders;

public class EscapeCharacter {
    private String character;

    public EscapeCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public char toChar() {
        return character.charAt(0);
    }

    public String toRegex() {
        return addEscape();
    }

    @Override
    public String toString() {
        return character;
    }

    private String addEscape() {
        StringBuilder sb = new StringBuilder();
        for (char c : character.toCharArray()) {
            if (isRegexSpecialChar(c)) {
                sb.append("\\");
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private boolean isRegexSpecialChar(char ch) {
        return "[]{}()\\^$.|?*+".indexOf(ch) != -1;
    }
}
