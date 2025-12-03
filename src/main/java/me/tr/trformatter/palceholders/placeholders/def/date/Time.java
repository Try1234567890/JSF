package me.tr.trformatter.palceholders.placeholders.def.date;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time extends Placeholder {
    public Time() {
        super(new UID("Time"));
    }

    public Time(Params params, Function[] functions, int start, int end) {
        super(new UID("Time"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        String format = getParams().asString(0);
        return LocalTime.now().format(DateTimeFormatter.ofPattern(format.isEmpty() ? "HH:mm:ss" : format));
    }

    @Override
    public Time newInstance(Params params, Function[] functions, int start, int end) {
        return new Time(params, functions, start, end);
    }
}
