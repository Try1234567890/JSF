package me.tr.trformatter.defaults.functions;

import me.tr.trformatter.components.Function;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

public class SubString extends Function {
    private static final UID FROM_KEY = new UID("from", "start");
    private static final UID TO_KEY = new UID("to", "end");

    public SubString() {
        super(new UID("sub_string"));
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
        int from = getFrom(params).intValue();
        int to = getTo(tagResult.length(), params).intValue();

        return tagResult.substring(from, to);
    }

    private Number getFrom(ParamsContainer params) {
        return params.getAs(FROM_KEY, Number.class).orElse(0);
    }

    private Number getTo(int length, ParamsContainer params) {
        return params.getAs(TO_KEY, Number.class).orElse(length);
    }

}
