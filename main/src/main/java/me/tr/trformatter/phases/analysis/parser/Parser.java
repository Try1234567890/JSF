package me.tr.trformatter.phases.analysis.parser;

import me.tr.trformatter.phases.evaluation.components.EvalComponent;

public interface Parser<T> {

    EvalComponent parse(T token);

}
