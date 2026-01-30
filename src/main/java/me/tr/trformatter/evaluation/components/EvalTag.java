package me.tr.trformatter.evaluation.components;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Tag;

public class EvalTag implements EvalComponent {
    private final Tag tag;
    private final ParamsContainer params;

    public EvalTag(Tag tag, ParamsContainer params) {
        this.tag = tag;
        this.params = params;
    }

    public Tag getPlaceholder() {
        return tag;
    }

    public ParamsContainer getParams() {
        return params;
    }


    public String evaluate() {
        return tag.evaluate(getParams());
    }
}
