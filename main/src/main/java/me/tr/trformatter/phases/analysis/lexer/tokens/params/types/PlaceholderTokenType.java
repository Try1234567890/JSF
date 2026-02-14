package me.tr.trformatter.phases.analysis.lexer.tokens.params.types;

import me.tr.trformatter.phases.analysis.lexer.defaults.PlaceholderLexer;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.parser.defaults.PlaceholderParser;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.phases.analysis.scanner.defaults.PlaceholderScanner;
import me.tr.trformatter.phases.evaluation.components.EvalPlaceholder;
import me.tr.trformatter.utility.Validator;

public class PlaceholderTokenType implements ParamType<String> {
    public static final PlaceholderTokenType TYPE = new PlaceholderTokenType();

    @Override
    public boolean isCorrectType(String obj) {
        return !PlaceholderScanner.INSTANCE.scan(obj).isEmpty();
    }

    @Override
    public String convert(String obj) {
        IndexedRawPlaceholder placeholder = PlaceholderScanner.INSTANCE.findFirstOrNull(obj);
        if (Validator.isNull(placeholder)) {
            new NullPointerException("An error occurs while scanning the placeholder. Converting failed, returning an empty string.").printStackTrace(System.err);
            return "";
        }

        PlaceholderToken token = PlaceholderLexer.INSTANCE.tokenize(placeholder);
        if (Validator.isNull(token)) {
            new NullPointerException("An error occurs while tokenizing the placeholder. Converting failed, returning an empty string.").printStackTrace(System.err);
            return "";
        }

        EvalPlaceholder eval = PlaceholderParser.INSTANCE.parse(token);
        if (Validator.isNull(eval)) {
            new NullPointerException("An error occurs while parsing the placeholder. Converting failed, returning an empty string.").printStackTrace(System.err);
            return "";
        }

        return eval.evaluate();
    }


    @Override
    public Class<String> getType() {
        return String.class;
    }


}
