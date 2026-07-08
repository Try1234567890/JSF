package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.tokens.components.ConditionToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.utility.Validator;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderLexer extends GenericLexer<IndexedRawPlaceholder> {
    public static final PlaceholderLexer INSTANCE = new PlaceholderLexer(CharacterSet.DEFAULT);

    protected PlaceholderLexer(CharacterSet characters) {
        super(characters);
    }

    public static PlaceholderLexer of(CharacterSet characters) {
        if (characters == null || characters.isDefault()) {
            return INSTANCE;
        }
        return new PlaceholderLexer(characters);
    }

    public static PlaceholderLexer of() {
        return INSTANCE;
    }

    @Override
    public PlaceholderToken tokenize(IndexedRawPlaceholder rawComponent) {

        if (Validator.isNull(rawComponent)) {
            new NullPointerException("The provided raw placeholder is null.").printStackTrace(System.err);
            return null;
        }

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
            PlaceholderToken token = tokenize(rawComponent);

            if (token == null) continue;

            tokens.add(token);
        }

        return tokens;
    }
}
