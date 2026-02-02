package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.FunctionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunction;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawFunctions;
import me.tr.trformatter.strings.CString;
import me.tr.trformatter.utility.Validator;

import java.util.ArrayList;
import java.util.List;

public class FunctionsLexer extends GenericLexer<IndexedRawFunction> {
    public static final FunctionsLexer INSTANCE = new FunctionsLexer();

    public FunctionsLexer(Characters characters) {
        super(characters);
    }

    public FunctionsLexer() {
        super();
    }

    public List<FunctionToken> tokenize(IndexedRawFunctions rawComponents) {
        return tokenize(rawComponents.functions());
    }

    public List<FunctionToken> tokenize(List<IndexedRawFunction> rawComponents) {
        List<FunctionToken> tokens = new ArrayList<>();

        for (IndexedRawFunction rawComponent : rawComponents) {
            FunctionToken token = tokenize(rawComponent);
            tokens.add(token);
        }

        return tokens;
    }

    @Override
    public FunctionToken tokenize(IndexedRawFunction rawComponent) {
        if (Validator.isNull(rawComponent)) {
            throw new NullPointerException("The provided raw component is null.");
        }

        NameToken name = getName(new CString(rawComponent.component()));
        List<ParamToken> params = getParams(rawComponent);

        return new FunctionToken(name, params);
    }
}
