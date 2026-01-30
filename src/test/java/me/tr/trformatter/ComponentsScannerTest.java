package me.tr.trformatter;

import me.tr.trformatter.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.analysis.lexer.tokens.defaults.PlaceholderLexer;
import me.tr.trformatter.analysis.parser.defaults.PlaceholderParser;
import me.tr.trformatter.analysis.scanner.defaults.PlaceholderScanner;
import me.tr.trformatter.analysis.scanner.results.IndexedRawPlaceholder;
import me.tr.trformatter.evaluation.components.EvalPlaceholder;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ComponentsScannerTest {

    @Test
    public void scan() {
        String text = "Placeholder Log: ${#[send_message(out=SOUT,message=\"Ciao questo é un test\") * %[to_lower_case(l_skip='0',r_skip='2')]] ^ @[if_time(time='16:00',comparator='>=')]}$";

        List<IndexedRawPlaceholder> rawPlaceholders = new PlaceholderScanner(text).scan();

        System.out.println("\n\n" + String.join("\n", rawPlaceholders.stream().map(IndexedRawPlaceholder::toString).toList()));


        List<PlaceholderToken> tokenPlaceholders = rawPlaceholders.stream()
                .map(raw -> new PlaceholderLexer(raw).tokenize())
                .toList();

        System.out.println("\n\n" + String.join("\n", tokenPlaceholders.stream().map(PlaceholderToken::toString).toList()));

        List<EvalPlaceholder> evalPlaceholders = new PlaceholderParser().parse(tokenPlaceholders);

        System.out.println("\n\n" + String.join("\n", evalPlaceholders.stream().map(EvalPlaceholder::evaluate).toList()));
    }
}
