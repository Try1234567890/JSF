package me.tr.trformatter.defaults.conditions.comparable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Comparator {

    MAJOR(">"),
    MAJOR_OR_EQUALS(">="),
    MINOR("<"),
    MINOR_OR_EQUALS("<="),
    EQUALS("=="),
    NOT_EQUALS("!=");

    private static final Logger LOGGER = LoggerFactory.getLogger(Comparator.class);
    private final String operator;

    Comparator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static Comparator parse(String str) {
        if (str == null) return EQUALS;

        for (Comparator comparator : Comparator.values()) {
            if (comparator.name().equalsIgnoreCase(str) || comparator.getOperator().equalsIgnoreCase(str)) {
                return comparator;
            }
        }

        LOGGER.warn("The comparator {} is not recognized. Using \"EQUALS\"", str);
        return EQUALS;
    }
}
