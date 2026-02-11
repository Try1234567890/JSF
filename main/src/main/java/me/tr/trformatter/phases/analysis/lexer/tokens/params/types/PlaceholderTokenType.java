package me.tr.trformatter.phases.analysis.lexer.tokens.params.types;

import me.tr.trformatter.phases.analysis.lexer.defaults.PlaceholderLexer;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.phases.analysis.parser.defaults.PlaceholderParser;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawPlaceholder;
import me.tr.trformatter.phases.analysis.scanner.defaults.PlaceholderScanner;
import me.tr.trformatter.phases.evaluation.components.EvalPlaceholder;
import me.tr.trformatter.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaceholderTokenType implements ParamType<String> {
    public static final PlaceholderTokenType TYPE = new PlaceholderTokenType();
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceholderTokenType.class);

    @Override
    public boolean isCorrectType(String obj) {
        return !PlaceholderScanner.INSTANCE.scan(obj).isEmpty();
    }

    @Override
    public String convert(String obj) {
        IndexedRawPlaceholder placeholder = PlaceholderScanner.INSTANCE.scanOne(obj);
        if (Validator.isNull(placeholder)) {
            LOGGER.error("An error occurs while scanning the placeholder. Converting failed, returning an empty string.");
            return "";
        }

        PlaceholderToken token = PlaceholderLexer.INSTANCE.tokenize(placeholder);
        if (Validator.isNull(token)) {
            LOGGER.error("An error occurs while tokenizing the placeholder. Converting failed, returning an empty string.");
            return "";
        }

        EvalPlaceholder eval = PlaceholderParser.INSTANCE.parse(token);
        if (Validator.isNull(eval)) {
            LOGGER.error("An error occurs while parsing the placeholder. Converting failed, returning an empty string.");
            return "";
        }

        return eval.evaluate();
    }


    @Override
    public Class<String> getType() {
        return String.class;
    }


}
