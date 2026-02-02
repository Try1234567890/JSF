package me.tr.trformatter.defaults.functions;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

import java.util.Arrays;
import java.util.Optional;

public class Split extends Function {

    public Split() {
        super(new UID("split"));
    }

    /**
     * Evaluate this component.
     *
     * @param tagResult
     * @param params    The params found for this component.
     * @return the string to replace with placeholder in raw text.
     */
    @Override
    public String evaluate(String tagResult, ParamsContainer params) {
        String split = getSplit(params);
        String[] parts = tagResult.split(split);

        Optional<String> delOpt = getDelimiter(params);

        if (delOpt.isPresent()) {
            String del = delOpt.get();
            return String.join(del, parts);
        }

        return Arrays.toString(parts);
    }

    private String getSplit(ParamsContainer params) {
        return params.getAs("split", String.class).orElseThrow(() -> new NullPointerException("\"split\" key is not specified in " + params));
    }

    private Optional<String> getDelimiter(ParamsContainer params) {
        return params.getAs("delimiter", String.class);
    }
}
