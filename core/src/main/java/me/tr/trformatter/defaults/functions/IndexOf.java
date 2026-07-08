package me.tr.trformatter.defaults.functions;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

public class IndexOf extends Function {


    public IndexOf() {
        super(new UID("index_of"));
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
        return String.valueOf(tagResult.indexOf(getText(params)));
    }

    private String getText(ParamsContainer params) {
        return params.getAs("text", String.class).orElseThrow(() -> new NullPointerException("\"text\" is not specified in " + params));
    }
}
