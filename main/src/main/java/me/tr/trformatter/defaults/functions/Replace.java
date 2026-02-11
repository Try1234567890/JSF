package me.tr.trformatter.defaults.functions;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

public class Replace extends Function {
    private static final UID OLD_KEY = new UID("old", "from", "before", "old_text", "OT");
    private static final UID NEW_KEY = new UID("new", "to", "after", "new_text", "NT");


    public Replace() {
        super(new UID("replace"));
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
        return tagResult.replace(getOldText(params), getNewText(params));
    }

    private String getOldText(ParamsContainer params) {
        return params.getAs(OLD_KEY, String.class).orElseThrow(() -> new NullPointerException("\"" + OLD_KEY + "\" is not specified in " + params));
    }

    private String getNewText(ParamsContainer params) {
        return params.getAs(NEW_KEY, String.class).orElseThrow(() -> new NullPointerException("\"" + NEW_KEY + "\" is not specified in " + params));
    }
}
