package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.FunctionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawTag;
import me.tr.trformatter.strings.CString;
import me.tr.trformatter.utility.Validator;

import java.util.List;

public class TagLexer extends GenericLexer<IndexedRawTag> {
    public static final TagLexer INSTANCE = new TagLexer();

    public TagLexer(Characters characters) {
        super(characters);
    }

    public TagLexer() {
        super();
    }

    @Override
    public TagToken tokenize(IndexedRawTag rawComponent) {
        if (Validator.isNull(rawComponent)) {
            throw new NullPointerException("The provided raw component is null.");
        }

        NameToken name = getName(new CString(rawComponent.component()));
        List<ParamToken> params = getParams(rawComponent);

        if (rawComponent.hasFunctions()) {
            List<FunctionToken> functions = FunctionsLexer.INSTANCE.tokenize(rawComponent.functions());
            return new TagToken(name, params, functions);
        }
        return new TagToken(name, params);
    }
}
