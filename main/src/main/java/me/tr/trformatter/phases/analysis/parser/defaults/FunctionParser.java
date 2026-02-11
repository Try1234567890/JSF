package me.tr.trformatter.phases.analysis.parser.defaults;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.FunctionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.parser.Parser;
import me.tr.trformatter.phases.evaluation.components.EvalFunction;
import me.tr.trformatter.phases.evaluation.components.EvalTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FunctionParser implements Parser<FunctionToken> {
    public static final FunctionParser INSTANCE = new FunctionParser();

    @Override
    public EvalFunction parse(FunctionToken token) {
        Optional<Function> funOpt = Function.getFunction(token.getName().getName());
        return funOpt.map(value -> new EvalFunction(value, token.params())).orElse(null);
    }

    public List<EvalFunction> parse(List<FunctionToken> list) {
        List<EvalFunction> components = new ArrayList<>();

        for (FunctionToken token : list) {
            components.add(parse(token));
        }

        return components;
    }
}
