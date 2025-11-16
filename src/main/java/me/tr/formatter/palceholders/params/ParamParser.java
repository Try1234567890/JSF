package me.tr.formatter.palceholders.params;

import me.tr.formatter.TrFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParamParser {

    public static Params parseForPlaceholder(String str) {
        return parse(str, TrFormatter.PLACEHOLDER_PARAMS_SEPARATOR.toChar(),
                new char[]{TrFormatter.PLACEHOLDER_PARAMS_START.toChar(), TrFormatter.PLACEHOLDER_PARAMS_END.toChar()});
    }


    public static Params parseForFunction(String str) {
        return parse(str, TrFormatter.FUNCTION_PARAMS_SEPARATOR.toChar(),
                new char[]{TrFormatter.FUNCTION_PARAMS_START.toChar(), TrFormatter.FUNCTION_PARAMS_END.toChar()});
    }

    public static Params parseForSubParams(String str) {
        return parse(str, TrFormatter.PARAM_PARAMS_SEPARATOR.toChar(),
                new char[]{TrFormatter.PARAM_PARAMS_START.toChar(), TrFormatter.PARAM_PARAMS_END.toChar()});
    }

    private static Params parse(String str, char separator, char[] exclude) {
        List<String> result = new ArrayList<>();
        int start = 0;
        int end = -1;
        int len = str.length();
        if (!Arrays.equals(str.toCharArray(), exclude)) { // Exclude void params like CP() or CP[]
            for (int i = 0; i <= len; i++) {
                if (i == len || str.charAt(i) == separator) {
                    while (start < i && str.charAt(start) == ' ') start++;
                    end = i - 1;
                    while (end >= start && str.charAt(end) == ' ') end--;
                    if (end >= start) {
                        result.add(str.substring(start, end + 1));
                    }
                    start = i + 1;
                }
            }
        }
        return new Params(str, start, end, result.toArray());
    }

}
