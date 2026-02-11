package me.tr.trformatter.defaults.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public enum Comparator {

    MAJOR(">", "major"),
    MAJOR_OR_EQUALS(">=", "major_or_equals"),
    MINOR("<", "minor"),
    MINOR_OR_EQUALS("<=", "minor_or_equals"),
    EQUALS("==", "equals"),
    NOT_EQUALS("!=", "not_equals");

    private static final Logger LOGGER = LoggerFactory.getLogger(Comparator.class);
    private final Set<String> operators;

    Comparator(String... operators) {
        this.operators = Set.of(operators);
    }

    public Set<String> getOperators() {
        return operators;
    }

    public static Comparator parse(String str) {
        if (str == null) {
            LOGGER.warn("The comparator is null. Using \"EQUALS\"");
            return EQUALS;
        }

        for (Comparator comparator : Comparator.values()) {
            if (comparator.name().equalsIgnoreCase(str)
                    || comparator.getOperators().contains(str)) {
                return comparator;
            }
        }

        LOGGER.warn("The comparator {} is not recognized. Using \"EQUALS\"", str);
        return EQUALS;
    }
}
