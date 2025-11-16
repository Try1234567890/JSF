package me.tr.trformatter.palceholders.functions;

import me.tr.trformatter.TrFormatter;
import me.tr.trformatter.palceholders.params.ParamParser;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.registries.Registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionParser {
    private static final Pattern FUNCTIONS = Pattern.compile("(\\w+)(?:%s([^\\[\\]]+)%s)?"
            .formatted(TrFormatter.FUNCTION_PARAMS_START.toRegex(), TrFormatter.FUNCTION_PARAMS_END.toRegex()));


    public static Function[] parse(String placeholder) {
        Matcher matcher = FUNCTIONS.matcher(placeholder);
        List<Function> functions = new ArrayList<>();
        while (matcher.find()) {
            String functionName = matcher.group(1);
            Function function = Registry.getFunctionRegistry().get(functionName);
            if (function != null) {
                String params = matcher.group(2);
                Params paramArray = new Params(placeholder, 0, 0);
                if (params != null) {
                    paramArray = ParamParser.parseForFunction(params);
                }
                try {
                    functions.add(function.getClass().getConstructor(Params.class).newInstance(paramArray));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("An error occurs while parsing " + functionName, e);
                }
            }
        }
        return functions.toArray(new Function[0]);
    }
}
