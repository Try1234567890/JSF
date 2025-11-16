package me.tr.trformatter.palceholders.functions.def;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.uids.UID;

public class Upper extends Function {
    public Upper() {
        super(new UID("Upper"));
    }

    public Upper(Params params) {
        super(new UID("Upper"), params, -1);
    }

    @Override
    public String process(String str) {
        Function sub = new Sub(getParams());
        String subStr = sub.process(str);
        if (subStr.equals(str)) return str.toUpperCase();
        int start = getParams().asInt(0);
        int end = getParams().asInt(1);
        return str.substring(0, start) + subStr.toUpperCase() + str.substring(end);
    }
}
