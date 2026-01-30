package me.tr.trformatter.analysis.lexer.tokens.defaults;

import me.tr.trformatter.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawPlaceholder;

public class PlaceholderLexer extends GenericLexer {

    public PlaceholderLexer(IndexedRawPlaceholder rawComponent, Characters characters) {
        super(rawComponent, characters);
    }

    public PlaceholderLexer(IndexedRawPlaceholder rawComponent) {
        super(rawComponent);
    }

    @Override
    public IndexedRawPlaceholder rawComponent() {
        return (IndexedRawPlaceholder) super.rawComponent();
    }

    @Override
    public PlaceholderToken tokenize() {
        TagToken tag = new TagLexer(rawComponent().tag()).tokenize();

        if (rawComponent().hasConditions()) {
            ConditionToken condition = new ConditionsLexer(rawComponent()).tokenize();
            return new PlaceholderToken(tag, condition);
        }

        return new PlaceholderToken(tag, null);
    }
}
