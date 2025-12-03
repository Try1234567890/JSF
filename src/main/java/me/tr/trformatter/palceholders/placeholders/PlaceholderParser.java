package me.tr.trformatter.palceholders.placeholders;

import me.tr.trformatter.TrFormatter;
import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.functions.FunctionParser;
import me.tr.trformatter.palceholders.params.ParamParser;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.registries.PlaceholdersRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderParser {
    private final static Pattern PLACEHOLDERS = Pattern.compile("%s(\\w+)(?:%s(.*?)%s)?%s(?:%s((?:\\w+(?:%s(.*?)%s)?)+)%s)?"
            .formatted(TrFormatter.START_PLACEHOLDER.toRegex(), TrFormatter.PLACEHOLDER_PARAMS_START.toRegex(), TrFormatter.PLACEHOLDER_PARAMS_END.toRegex(),
                    TrFormatter.END_PLACEHOLDER.toRegex(), TrFormatter.START_FUNCTION.toRegex(), TrFormatter.FUNCTION_PARAMS_START.toRegex(),
                    TrFormatter.FUNCTION_PARAMS_END.toRegex(), TrFormatter.END_FUNCTION.toRegex()));

    public static PlaceholderResolver parse(String str) {
        final List<Placeholder> placeholders = new ArrayList<>();
        Matcher matcher = PLACEHOLDERS.matcher(str);
        while (matcher.find()) {
            String nameStr = matcher.group(1);
            String paramsStr = matcher.group(2);
            String functionsStr = matcher.group(3);
            Placeholder placeholder = PlaceholdersRegistry.getInstance().get(nameStr);
            if (placeholder != null) {
                Params params = new Params("", 0, 0);
                Function[] functions = new Function[0];
                if (paramsStr != null && !paramsStr.isEmpty()) {
                    params = ParamParser.parseForPlaceholder(paramsStr);
                }
                if (functionsStr != null && !functionsStr.isEmpty()) {
                    functions = FunctionParser.parse(functionsStr);
                }
                placeholders.add(
                        placeholder.newInstance(params, functions, matcher.start(), matcher.end())
                );
            }
        }
        return new PlaceholderResolver(placeholders, str);
    }

}
