package me.tr.trformatter.analysis.parser;

import me.tr.trformatter.evaluation.components.EvalComponent;

public interface Parser<T> {

    EvalComponent parse(T token);

}
