package me.tr.trformatter.phases.evaluation.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.evaluation.defaults.TagEvaluator;

public class EvalTag implements EvalComponent {
    private final Tag tag;
    private final ParamsContainer params;

    public EvalTag(Tag tag, ParamsContainer params) {
        this.tag = tag;
        this.params = params;
    }

    public Tag getTag() {
        return tag;
    }

    public ParamsContainer getParams() {
        return params;
    }


    public String evaluate() {
        return TagEvaluator.INSTANCE.evaluate(this);
    }
}
