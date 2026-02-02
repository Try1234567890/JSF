package me.tr.trformatter.phases.analysis.lexer.defaults;

import me.tr.trformatter.phases.analysis.lexer.Lexer;
import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawComponent;
import me.tr.trformatter.phases.analysis.scanner.components.ParameterizedIndexedRawComponent;
import me.tr.trformatter.strings.CString;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericLexer<T extends IndexedRawComponent> implements Lexer<T> {
    private final Characters characters;

    public GenericLexer(Characters characters) {
        this.characters = characters != null ? characters : new Characters();
    }

    public GenericLexer() {
        this(new Characters());
    }

    public Characters characters() {
        return characters;
    }

    protected NameToken getName(CString text) {
        int to = text.indexOfIgnoringStrings(characters().getOpenParams());

        if (to == -1) {
            to = text.length();
        }

        return new NameToken(text.substring(0, to));
    }

    protected List<ParamToken> getParams(ParameterizedIndexedRawComponent parameterizedComponent) {
        if (!parameterizedComponent.hasParams()) {
            return new ArrayList<>();
        }

        return parameterizedComponent.asList().stream()
                .map(raw -> ParamToken.of(raw.name(), raw.value()))
                .toList();
    }
}





















