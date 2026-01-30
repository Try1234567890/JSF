package me.tr.trformatter.analysis.lexer.tokens.defaults;

import me.tr.trformatter.analysis.lexer.Lexer;
import me.tr.trformatter.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawComponent;
import me.tr.trformatter.analysis.scanner.results.ParameterizedIndexedRawComponent;
import me.tr.trformatter.strings.CString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericLexer implements Lexer {
    private final IndexedRawComponent rawComponent;
    private final CString cStringRawComponent;
    private final Characters characters;

    public GenericLexer(IndexedRawComponent rawComponent, Characters characters) {
        this.rawComponent = rawComponent;
        this.cStringRawComponent = new CString(rawComponent.component());
        this.characters = characters != null ? characters : new Characters();
    }

    public GenericLexer(IndexedRawComponent rawComponent) {
        this(rawComponent, new Characters());
    }

    public IndexedRawComponent rawComponent() {
        return rawComponent;
    }

    public Characters characters() {
        return characters;
    }

    public CString asCString() {
        return cStringRawComponent;
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





















