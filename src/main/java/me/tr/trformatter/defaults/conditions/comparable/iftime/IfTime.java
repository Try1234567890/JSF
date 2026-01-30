package me.tr.trformatter.defaults.conditions.comparable.iftime;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Condition;
import me.tr.trformatter.defaults.conditions.comparable.Comparator;
import me.tr.trformatter.uids.UID;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class IfTime extends Condition {
    private static final Map<Comparator, Function<LocalTime, Boolean>> COMPARATORS = new HashMap<>(
            Map.ofEntries(
                    Map.entry(Comparator.MAJOR, (lt) -> lt.isAfter(LocalTime.now())),
                    Map.entry(Comparator.MAJOR_OR_EQUALS, (lt) -> lt.equals(LocalTime.now()) || lt.isAfter(LocalTime.now())),
                    Map.entry(Comparator.MINOR, (lt) -> lt.isBefore(LocalTime.now())),
                    Map.entry(Comparator.MINOR_OR_EQUALS, (lt) -> lt.equals(LocalTime.now()) || lt.isBefore(LocalTime.now())),
                    Map.entry(Comparator.EQUALS, (lt) -> lt.equals(LocalTime.now())),
                    Map.entry(Comparator.NOT_EQUALS, (lt) -> !lt.equals(LocalTime.now()))
            )
    );

    public IfTime() {
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
        LocalTime time = getTime(params);

        return evaluate(getComparator(params), time);
    }

    private boolean evaluate(Comparator comparator, LocalTime time) {
        return COMPARATORS.get(comparator).apply(time);
    }

    private LocalTime getTime(ParamsContainer params) {
        String time = params.getAs("time", String.class);

        if (time == null) {
            throw new NullPointerException("The time of if_time condition is null.");
        }

        String format = getFormat(params);
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("The time of if_time condition is not valid or is not formatted correctly. The found format is: " + format, e);
        }
    }

    private String getFormat(ParamsContainer params) {
        String format = params.getAs("format", String.class);
        return format != null ? format : "HH:mm";
    }

    private Comparator getComparator(ParamsContainer params) {
        String comparator = params.getAs("comparator", String.class);
        return Comparator.parse(comparator);
    }
}
