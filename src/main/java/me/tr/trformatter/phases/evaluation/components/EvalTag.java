package me.tr.trformatter.phases.evaluation.components;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.phases.evaluation.defaults.TagEvaluator;

import java.util.List;

public class EvalTag implements EvalComponent {
    private final Tag tag;
    private final List<EvalFunction> functions;
    private final ParamsContainer params;

    public EvalTag(Tag tag, List<EvalFunction> functions, ParamsContainer params) {
        this.tag = tag;
        this.functions = functions;
        this.params = params;
    }

    public Tag getTag() {
        return tag;
    }

    public List<EvalFunction> getFunctions() {
        return functions;
    }

    public ParamsContainer getParams() {
        return params;
    }


    public String evaluate() {
        return TagEvaluator.INSTANCE.evaluate(this);
    }
}
