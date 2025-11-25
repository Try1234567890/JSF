package me.tr.trformatter;

import me.tr.trformatter.palceholders.EscapeCharacter;
import me.tr.trformatter.palceholders.placeholders.PlaceholderParser;

public class TrFormatter {
    public static EscapeCharacter START_PLACEHOLDER = new EscapeCharacter("$[");
    public static EscapeCharacter END_PLACEHOLDER = new EscapeCharacter("];");
    public static EscapeCharacter START_FUNCTION = new EscapeCharacter("{");
    public static EscapeCharacter END_FUNCTION = new EscapeCharacter("}");
    public static EscapeCharacter START_TAG = new EscapeCharacter("${{");
    public static EscapeCharacter END_TAG = new EscapeCharacter("}}");

    public static EscapeCharacter PLACEHOLDER_PARAMS_START = new EscapeCharacter("(");
    public static EscapeCharacter PLACEHOLDER_PARAMS_END = new EscapeCharacter(")");
    public static EscapeCharacter PLACEHOLDER_PARAMS_SEPARATOR = new EscapeCharacter(",");
    public static EscapeCharacter FUNCTION_PARAMS_START = new EscapeCharacter("[");
    public static EscapeCharacter FUNCTION_PARAMS_END = new EscapeCharacter("]");
    public static EscapeCharacter FUNCTION_PARAMS_SEPARATOR = new EscapeCharacter(",");
    public static EscapeCharacter PARAM_PARAMS_START = new EscapeCharacter("[");
    public static EscapeCharacter PARAM_PARAMS_END = new EscapeCharacter("]");
    public static EscapeCharacter PARAM_PARAMS_SEPARATOR = new EscapeCharacter("&");


    public static String resolvePlaceholders(String str) {
        return PlaceholderParser.parse(str).resolve();
    }


}
