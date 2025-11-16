package me.tr.formatter.palceholders.placeholders.def.date;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.uids.UID;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        DateFormat dateFormat = new SimpleDateFormat(format.isEmpty() ? "yyyy-MM-dd HH:mm:ss" : format);
        return dateFormat.format(new java.util.Date());
    }
}
