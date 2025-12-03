package me.tr.trformatter.palceholders.placeholders.def.date;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date extends Placeholder {
    public Date() {
        super(new UID("Date"));
    }

    public Date(Params params, Function[] functions, int start, int end) {
        super(new UID("Date"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        String format = getParams().asString(0);
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format.isEmpty() ? "yyyy-MM-dd" : format));
    }

    @Override
    public Date newInstance(Params params, Function[] functions, int start, int end) {
        return new Date(params, functions, start, end);
    }
}
