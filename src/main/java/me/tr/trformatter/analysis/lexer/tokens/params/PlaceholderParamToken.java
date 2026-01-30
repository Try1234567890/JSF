package me.tr.trformatter.analysis.lexer.tokens.params;

import me.tr.trformatter.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.analysis.lexer.tokens.params.types.PlaceholderTokenType;

import java.util.Optional;

public class PlaceholderParamToken extends ParamToken {

    public PlaceholderParamToken(String name, TagToken value) {
        super(name, PlaceholderTokenType.TYPE, value);
    }

    @Override
    public String asString() {
        Optional<TagToken> value = getValueAs(TagToken.class);
        return getName() + "=" + (value.isPresent() ? value.get().asString() : "null");
    }
}
