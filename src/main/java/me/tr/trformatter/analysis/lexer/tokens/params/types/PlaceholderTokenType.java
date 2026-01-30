package me.tr.trformatter.analysis.lexer.tokens.params.types;

import me.tr.trformatter.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.analysis.lexer.tokens.defaults.PlaceholderLexer;
import me.tr.trformatter.analysis.scanner.defaults.PlaceholderScanner;

public class PlaceholderTokenType implements ParamType<PlaceholderToken> {
    public static final PlaceholderTokenType TYPE = new PlaceholderTokenType();

    @Override
    public boolean isCorrectType(String obj) {
        return !new PlaceholderScanner(obj).scan().isEmpty();
    }

    @Override
    public PlaceholderToken convert(String obj) {
        return new PlaceholderLexer(new PlaceholderScanner(obj).scan().getFirst()).tokenize();
    }


    @Override
    public Class<PlaceholderToken> getType() {
        return PlaceholderToken.class;
    }


}
