package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderLexer extends GenericLexer<IndexedRawPlaceholder> {
    public static final PlaceholderLexer INSTANCE = new PlaceholderLexer();

    public PlaceholderLexer(Characters characters) {
        super(characters);
    }

    public PlaceholderLexer() {
        super();
    }

    @Override
    public PlaceholderToken tokenize(IndexedRawPlaceholder rawComponent) {
        TagToken tag = TagLexer.INSTANCE.tokenize(rawComponent.tag());

        if (rawComponent.hasConditions()) {
            ConditionToken condition = ConditionsLexer.INSTANCE.tokenize(rawComponent.conditions());
            return new PlaceholderToken(tag, condition, rawComponent.start(), rawComponent.end());
        }

        return new PlaceholderToken(tag, null, rawComponent.start(), rawComponent.end());
    }
    public List<PlaceholderToken> tokenize(List<IndexedRawPlaceholder> rawComponents) {
        List<PlaceholderToken> tokens = new ArrayList<>();

        for (IndexedRawPlaceholder rawComponent : rawComponents) {
            tokens.add(tokenize(rawComponent));
        }

        return tokens;
    }
}
