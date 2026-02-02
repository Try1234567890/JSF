package me.tr.trformatter;

import me.tr.trformatter.phases.analysis.lexer.defaults.PlaceholderLexer;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.parser.defaults.PlaceholderParser;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.phases.analysis.scanner.defaults.PlaceholderScanner;
import me.tr.trformatter.phases.evaluation.components.EvalPlaceholder;
import me.tr.trformatter.phases.evaluation.defaults.PlaceholderEvaluator;
import me.tr.trformatter.phases.synthesis.interpolator.PlaceholderInterpolator;
import me.tr.trformatter.phases.synthesis.interpolator.components.IndexedPlaceholder;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ComponentsScannerTest {

    @Test
    public void scan() {
        String text = "Placeholder Log: ${#[system_env(key='user.home')]}$ && ${#[system_env(key=${#[read_file(path='F:\\log.txt')]}$)]}$";

        List<IndexedRawPlaceholder> rawPlaceholders = new PlaceholderScanner().scan(text);

        System.out.println("\n\n" + String.join("\n", rawPlaceholders.stream().map(IndexedRawPlaceholder::toString).toList()));


        List<PlaceholderToken> tokenPlaceholders = rawPlaceholders.stream()
                .map(raw -> new PlaceholderLexer().tokenize(raw))
                .toList();

        System.out.println("\n\n" + String.join("\n", tokenPlaceholders.stream().map(PlaceholderToken::toString).toList()));

        List<EvalPlaceholder> evalPlaceholders = PlaceholderParser.INSTANCE.parse(tokenPlaceholders);

        System.out.println("\n\n" + String.join("\n", evalPlaceholders.stream().map(EvalPlaceholder::toString).toList()));

        List<IndexedPlaceholder> placeholders = PlaceholderEvaluator.INSTANCE.evaluate(evalPlaceholders);

        System.out.println("\n\n" + String.join("\n", placeholders.stream().map(IndexedPlaceholder::toString).toList()));

        String newText = PlaceholderInterpolator.INSTANCE.interpolate(text, placeholders);

        System.out.println(newText);
    }
}
