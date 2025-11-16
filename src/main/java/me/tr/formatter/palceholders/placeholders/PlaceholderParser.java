package me.tr.formatter.palceholders.placeholders;

import me.tr.formatter.TrFormatter;
import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.functions.FunctionParser;
import me.tr.formatter.palceholders.params.ParamParser;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.registries.PlaceholdersRegistry;

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
                try {
                    placeholders.add(placeholder.getClass().getConstructor(Params.class, Function[].class, int.class, int.class).newInstance(params, functions, matcher.start(), matcher.end()));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("An error occurs while parsing " + nameStr, e);
                }
            }
        }
        return new PlaceholderResolver(placeholders, str);
    }

}
