package me.tr.trformatter.phases.evaluation;

import me.tr.trformatter.phases.evaluation.components.EvalComponent;

public interface Evaluator<T extends EvalComponent, R> {

    R evaluate(T component);

}
