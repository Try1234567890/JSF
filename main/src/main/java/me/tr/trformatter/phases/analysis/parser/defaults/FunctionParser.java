package me.tr.trformatter.phases.analysis.parser.defaults;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.phases.analysis.exceptions.ComponentNotFound;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.FunctionToken;
import me.tr.trformatter.phases.analysis.parser.Parser;
import me.tr.trformatter.phases.evaluation.components.EvalFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FunctionParser implements Parser<FunctionToken> {
    public static final FunctionParser INSTANCE = new FunctionParser();

    @Override
    public EvalFunction parse(FunctionToken token) {
        if (token == null) return null;

        Optional<Function> funOpt = Function.getFunction(token.getName().getName());

        return funOpt.map(value -> new EvalFunction(value, token.params())).orElse(null);
    }

    public List<EvalFunction> parseAll(List<FunctionToken> list) {
        List<EvalFunction> components = new ArrayList<>();

        for (FunctionToken token : list) {
            EvalFunction function = parse(token);

            if (function == null) {
                ComponentNotFound.function(token.getName().getName()).printStackTrace(System.err);
                continue;
            }

            components.add(function);
        }

        return components;
    }
}
