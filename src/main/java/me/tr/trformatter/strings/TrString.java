package me.tr.trformatter.strings;

import me.tr.trformatter.TrUtility;
import me.tr.trformatter.TrValidator;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.strings.format.FormatResult;
import me.tr.trformatter.strings.format.TextFormat;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TrString {
    private String string;

    /**
     * @param string The string to set for this instance.
     * @throws NullPointerException if the provided string is null or empty.
     */
    public TrString(String string) {
        TrValidator.isNull(string, "The string is null");
        this.string = string;
    }

    public TrString(byte[] bytes) {
        this(new String(bytes));
    }

    public TrString(StringBuffer buffer) {
        this(buffer.toString());
    }

    public TrString(StringBuilder builder) {
        this(builder.toString());
    }

    public TrString(char[] chars) {
        this(String.valueOf(chars));
    }

    /**
     * Retrieve the substring inside the provided
     * characters.
     * <p>
     * Both chars are excluded from the final string.
     * <p>
     * <ul><b>For example ¬</b></ul>
     * <pre>
     *  From string: "This is an $[EXAMPLE]"
     *  getInsideOf('[', ']') returns "EXAMPLE"
     * </pre>
     *
     * @param start First character to search; This search starts from left.
     * @param end   Last character to search; This search starts from right.
     * @return A substring inside the string from the start to the end character.
     * @throws NullPointerException if one of the characters is null.
     */
    public String getInsideOf(char start, char end) {
        TrValidator.isNull(start, "The start char is null.");
        TrValidator.isNull(end, "The end char is null.");
        int s = getString().indexOf(start);
        int e = getString().lastIndexOf(end);
        return getString().substring(s + 1, e);
    }

    /**
     * Retrieve the substring inside the provided
     * character.
     * <p>
     * Both chars are excluded from the final string.
     * <p>
     * <ul><b>For example ¬</b></ul>
     * <pre>
     *  From string: "This is an -example-"
     *  getInsideOf('-') returns "example"
     * </pre>
     *
     * @param symbol The character to search;
     * @return A substring inside the string from the start to the end character.
     * @throws NullPointerException if the provided character is null.
     */
    public String getInsideOf(char symbol) {
        TrValidator.isNull(symbol, "The symbol is null.");
        return getInsideOf(symbol, symbol);
    }

    /**
     * Replace all provided placeholders from the string
     *
     * @param placeholders The placeholders to replace.
     * @return A new string with all placeholders replaced.
     */
    public String replace(Placeholder... placeholders) {
        String newStr = getString();
        for (Placeholder placeholder : placeholders)
            newStr = placeholder.process(getString());
        return newStr;
    }

    /**
     * Replace all provided placeholders from the string
     * and apply it to this instance.
     *
     * @param placeholders The placeholders to replace.
     * @return A new string with all placeholders replaced.
     */
    public String replaceAndApply(Placeholder... placeholders) {
        String newStr = replace(placeholders);
        setString(newStr);
        return newStr;
    }

    /**
     * Replace all spaces from the string.
     *
     * @return A new string without spaces.
     */
    public String removeSpaces() {
        return getString().replace(" ", "");
    }

    /**
     * Reverse the order of string characters.
     *
     * <ul><b>For example ¬</b></ul>
     * <ul>
     *     For the string "Hello world"
     *     reverse() returns "dlrow olleH"
     * </ul>
     *
     * @return A reversed string.
     */
    public String reverse() {
        StringBuilder sb = new StringBuilder();

        for (int i = getString().length() - 1; i >= 0; i--)
            sb.append(getString().charAt(i));

        return sb.toString();
    }

    /**
     * Checks if the string is palindrome.
     *
     * <p>
     *     <ul>This method ignore spaces</ul>
     *     <ul>This method ignore cases</ul>
     * </p>
     *
     * @return true if is palindrome, otherwise false.
     */
    public boolean isPalindrome() {
        String reversed = reverse().replace(" ", "").toLowerCase();
        return reversed.equals(getString().replace(" ", "").toLowerCase());
    }

    /**
     * Truncate the string at the provided index.
     *
     * @param index The index to truncate string at.
     * @return A new string representing the substring from the index 0 to the provided index.
     * @throws NullPointerException if the index is null
     */
    public String truncate(int index) {
        TrValidator.isNull(index, "The index is minus of 0.");
        return getString().substring(0, index);
    }

    /**
     * Truncate the string at the provided index
     * and add the provided string to the end.
     *
     * @param index The index to truncate string at.
     * @param end   A string to add at the end of the truncated string.
     * @return A new string representing the substring from the index 0 to the provided index.
     * @throws NullPointerException if the index is null
     */
    public String truncate(int index, String end) {
        return truncate(index) + (TrValidator.isNull(end) ? "" : end);
    }

    /**
     * Count the amount of the provided character
     * the string contains.
     *
     * @param character The character to count.
     * @return The amount of characters found.
     * @throws NullPointerException if the character is null.
     */
    public int count(char character) {
        TrValidator.isNull(character, "The character is null.");
        int count = 0;

        for (char c : getString().toCharArray()) {
            if (c == character)
                count++;
        }

        return count;
    }

    /**
     * Replace the text between the character with the
     * provided replacement.
     *
     * @param start       First character to search; This search starts from left.
     * @param end         Last character to search; This search starts from right.
     * @param replacement The replacement to the found string.
     * @return The string with text replaced.
     * @throws NullPointerException If one of the parameters is null.
     */
    public String replaceBetween(char start, char end, String replacement) {
        TrValidator.isNull(start, "The start char is null.");
        TrValidator.isNull(end, "The end char is null.");
        TrValidator.isNull(replacement, "The replacement string is null.");
        int s = getString().indexOf(start);
        int e = getString().lastIndexOf(end);
        return getString().substring(0, s) + replacement + getString().substring(e + 1);
    }

    /**
     * Split the string in groups string
     * with provided length.
     *
     * @param length The length of the groups.
     * @return A list of string.
     */
    public List<String> splitByLength(int length) {
        List<String> result = new ArrayList<>();
        int p = 0; // previous index;
        int c = 0; // current index;
        while ((c += length) < getString().length()) {
            result.add(getString().substring(p, c));
            p = c;
        }
        // Add the remaining characters
        if (c != getString().length())
            result.add(getString().substring(p));
        return result;
    }

    /**
     * Checks if the string contains the provided
     * string is present in the string.
     *
     * @param search If the string to check for.
     * @return {@code true} if string contains the provided one, otherwise {@code false}.
     * @throws NullPointerException if the string is null
     */
    public boolean containsIgnoreCase(String search) {
        TrValidator.isNull(search, "The string is null");
        return getString().toLowerCase().contains(search.toLowerCase());
    }

    /**
     * Convert the string to a new case.
     *
     * @param from The starting case.
     * @param to   The destination case.
     * @return An instance of {@link FormatResult} that contains the result.
     * @throws NullPointerException if at least one of the case is null.
     * @throws RuntimeException     if an error occur while creating a new instance of TextFormats.
     */
    public FormatResult toCaseFrom(Class<? extends TextFormat> from, Class<? extends TextFormat> to) {
        try {
            return toCaseFrom(TrUtility.instantiate(from, getString()),
                    TrUtility.instantiate(to, getString()));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException("An error occurs while creating a new instance of the TextFormat class." + e.getMessage());

        }
    }

    /**
     * Convert the string to a new case.
     *
     * @param from The starting case.
     * @param to   The destination case.
     * @return An instance of {@link FormatResult} that contains the result.
     * @throws NullPointerException if at least one of the case is null.
     */
    public FormatResult toCaseFrom(TextFormat from, TextFormat to) {
        TrValidator.isNull(from, "The from case is null.");
        TrValidator.isNull(to, "The to case is null.");
        return from.toCaseFrom(to);
    }


    /**
     * Get the string of this instance.
     *
     * @return The string of this instance.
     */
    public String getString() {
        return string;
    }

    private void setString(String string) {
        this.string = string;
    }
}



















