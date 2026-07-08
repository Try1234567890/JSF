package me.tr.trformatter.phases.analysis.lexer;

import me.tr.trformatter.phases.analysis.lexer.tokens.Token;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawComponent;

public interface Lexer<T extends IndexedRawComponent> {

    Token tokenize(T rawComponent);

}
