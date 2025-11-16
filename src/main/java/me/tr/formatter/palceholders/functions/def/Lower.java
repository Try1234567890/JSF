package me.tr.formatter.palceholders.functions.def;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.uids.UID;

public class Lower extends Function {
    public Lower() {
        super(new UID("Lower"));
    }

    public Lower(Params params) {
        super(new UID("Lower"), params, -1);
    }

    @Override
    public String process(String str) {
        Function sub = new Sub(getParams());
        String subStr = sub.process(str);
        if (subStr.equals(str)) return str.toLowerCase();
        int start = getParams().asInt(0);
        int end = getParams().asInt(1);
        return str.substring(0, start) + subStr.toLowerCase() + str.substring(end);
    }
}
