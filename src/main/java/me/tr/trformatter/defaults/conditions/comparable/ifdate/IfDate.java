package me.tr.trformatter.defaults.conditions.comparable.ifdate;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Condition;
import me.tr.trformatter.defaults.conditions.comparable.Comparator;
import me.tr.trformatter.uids.UID;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class IfDate extends Condition {
    private static final Map<Comparator, Function<LocalDate, Boolean>> COMPARATORS = new HashMap<>(
            Map.ofEntries(
                    Map.entry(Comparator.MAJOR, (lt) -> lt.isAfter(LocalDate.now())),
                    Map.entry(Comparator.MAJOR_OR_EQUALS, (lt) -> lt.equals(LocalTime.now()) || lt.isAfter(LocalDate.now())),
                    Map.entry(Comparator.MINOR, (lt) -> lt.isBefore(LocalDate.now())),
                    Map.entry(Comparator.MINOR_OR_EQUALS, (lt) -> lt.equals(LocalTime.now()) || lt.isBefore(LocalDate.now())),
                    Map.entry(Comparator.EQUALS, (lt) -> lt.equals(LocalDate.now())),
                    Map.entry(Comparator.NOT_EQUALS, (lt) -> !lt.equals(LocalDate.now()))
            )
    );

    public IfDate() {
        super(new UID("if_time"));
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return {@code true} if the placeholder ends successfully (so the process continue), otherwise {@code false}.
     */
    @Override
    public boolean evaluate(ParamsContainer params) {
        LocalDate date = getDate(params);

        return evaluate(getComparator(params), date);
    }

    public boolean evaluate(Comparator comparator, LocalDate date) {
        return COMPARATORS.get(comparator).apply(date);
    }

    private LocalDate getDate(ParamsContainer params) {
        String time = params.getAs("date", String.class);

        if (time == null) {
            throw new NullPointerException("The time of if_date condition is null.");
        }

        String format = getFormat(params);
        try {
            return LocalDate.parse(time, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("The time of if_time condition is not valid or is not formatted correctly. The found format is: " + format, e);
        }
    }

    private String getFormat(ParamsContainer params) {
        String format = params.getAs("format", String.class);
        return format != null ? format : "dd.MM.yyyy";
    }

    private Comparator getComparator(ParamsContainer params) {
        String comparator = params.getAs("comparator", String.class);
        return Comparator.parse(comparator);
    }
}
