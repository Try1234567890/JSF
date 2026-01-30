package me.tr.trformatter.analysis.lexer.tokens.defaults;

import me.tr.trformatter.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawTag;

import java.util.List;

public class TagLexer extends GenericLexer {

    public TagLexer(IndexedRawTag rawComponent, Characters characters) {
        super(rawComponent, characters);
    }

    public TagLexer(IndexedRawTag rawComponent) {
        super(rawComponent);
    }

    @Override
    public IndexedRawTag rawComponent() {
        return (IndexedRawTag) super.rawComponent();
    }

    @Override
    public TagToken tokenize() {
        NameToken name = getName(asCString());
        List<ParamToken> params = getParams(rawComponent());

        return new TagToken(name, params);
    }
}
