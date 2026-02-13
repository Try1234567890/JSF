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

import java.util.List;

public class TrFormatterAPI {
    private static final PlaceholderScanner SCANNER = new PlaceholderScanner();
    private static final PlaceholderLexer TOKENIZER = new PlaceholderLexer();
    private static final PlaceholderParser PARSER = new PlaceholderParser();
    private static final PlaceholderEvaluator EVALUATOR = new PlaceholderEvaluator();
    private static final PlaceholderInterpolator INTERPOLATOR = new PlaceholderInterpolator();

    private TrFormatterAPI() {

    }

    public static String format(String text) {
        List<IndexedRawPlaceholder> raws = SCANNER.scan(text);
        List<PlaceholderToken> tokens = TOKENIZER.tokenize(raws);
        List<EvalPlaceholder> evaluators = PARSER.parse(tokens);
        List<IndexedPlaceholder> placeholders = EVALUATOR.evaluate(evaluators);

        return INTERPOLATOR.interpolate(text, placeholders);
    }
}
