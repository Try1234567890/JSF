package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;

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
}
