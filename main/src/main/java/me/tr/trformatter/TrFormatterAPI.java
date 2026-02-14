package me.tr.trformatter;

import me.tr.trformatter.phases.analysis.lexer.defaults.PlaceholderLexer;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.parser.defaults.PlaceholderParser;
import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.phases.analysis.scanner.defaults.PlaceholderScanner;
import me.tr.trformatter.phases.evaluation.components.EvalPlaceholder;
import me.tr.trformatter.phases.evaluation.defaults.PlaceholderEvaluator;
import me.tr.trformatter.phases.synthesis.interpolator.PlaceholderInterpolator;
import me.tr.trformatter.phases.synthesis.interpolator.components.IndexedPlaceholder;

import java.util.List;

public class TrFormatterAPI {

    private TrFormatterAPI() {
    }

    public static String format(String text) {
        return format(text, CharacterSet.DEFAULT);
    }

    public static String format(String text, CharacterSet characters) {
        List<IndexedRawPlaceholder> raws = PlaceholderScanner.of(characters).scan(text);
        if (raws.isEmpty()) {
            // Simple optimization, do not continue if there is no placeholders.
            return text;
        }

        List<PlaceholderToken> tokens = PlaceholderLexer.of(characters).tokenize(raws);
        List<EvalPlaceholder> evaluators = PlaceholderParser.INSTANCE.parseAll(tokens);
        List<IndexedPlaceholder> placeholders = PlaceholderEvaluator.INSTANCE.evaluate(evaluators);
        return PlaceholderInterpolator.INSTANCE.interpolate(text, placeholders);
    }
}
