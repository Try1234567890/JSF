package me.tr.trformatter.defaults.conditions;

import me.tr.trformatter.components.Condition;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class IfDate extends Condition {
    private static final Map<Comparator, Function<LocalDate, Boolean>> COMPARATORS = new HashMap<>(
            Map.ofEntries(
                    Map.entry(Comparator.MAJOR, (lt) -> lt.isAfter(LocalDate.now())),
                    Map.entry(Comparator.MAJOR_OR_EQUALS, (lt) -> lt.equals(LocalDate.now()) || lt.isAfter(LocalDate.now())),
                    Map.entry(Comparator.MINOR, (lt) -> lt.isBefore(LocalDate.now())),
                    Map.entry(Comparator.MINOR_OR_EQUALS, (lt) -> lt.equals(LocalDate.now()) || lt.isBefore(LocalDate.now())),
                    Map.entry(Comparator.EQUALS, (lt) -> lt.equals(LocalDate.now())),
                    Map.entry(Comparator.NOT_EQUALS, (lt) -> !lt.equals(LocalDate.now()))
            )
    );

    public IfDate() {
        super(new UID("if_date"));
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

        return COMPARATORS.get(getComparator(params)).apply(date);
    }

    private LocalDate getDate(ParamsContainer params) {
        Optional<String> dateOpt = params.getAs("date", String.class);

        if (dateOpt.isEmpty()) {
            throw new NullPointerException("The time of if_date condition is null.");
        }

        String date = dateOpt.get();

        String format = getFormat(params);
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("The time of if_time condition is not valid or is not formatted correctly. The found format is: " + format, e);
        }
    }

    private String getFormat(ParamsContainer params) {
        return params.getAs("format", String.class).orElse("dd.MM.yyyy");
    }

    @Override
    public Comparator getComparator(ParamsContainer params) {
        String comparator = params.getAsOrNull("comparator", String.class);
        return Comparator.parse(comparator);
    }
}
