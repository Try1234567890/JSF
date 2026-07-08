package me.tr.trformatter.defaults.conditions;


import me.tr.trformatter.phases.analysis.exceptions.ComponentNotFound;

import java.util.Set;

public enum Comparator {

    MAJOR(">", "major"),
    MAJOR_OR_EQUALS(">=", "major_or_equals"),
    MINOR("<", "minor"),
    MINOR_OR_EQUALS("<=", "minor_or_equals"),
    EQUALS("==", "equals"),
    NOT_EQUALS("!=", "not_equals");

    private final Set<String> operators;

    Comparator(String... operators) {
        this.operators = Set.of(operators);
    }

    public Set<String> getOperators() {
        return operators;
    }

    public static Comparator parse(String str) {
        if (str == null) {
            new ComponentNotFound("The comparator is null. Using \"EQUALS\"").printStackTrace(System.err);
            return EQUALS;
        }

        for (Comparator comparator : Comparator.values()) {
            if (comparator.name().equalsIgnoreCase(str)
                    || comparator.getOperators().contains(str)) {
                return comparator;
            }
        }

        new ComponentNotFound("The comparator " + str + " is not recognized. Using \"EQUALS\"").printStackTrace(System.err);
        return EQUALS;
    }
}
