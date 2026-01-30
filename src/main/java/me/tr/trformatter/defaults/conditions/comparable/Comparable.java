package me.tr.trformatter.defaults.conditions.comparable;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;

public interface Comparable {

    Comparator getComparator(ParamsContainer params);

}
