package me.tr.trformatter.palceholders.functions.def;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.uids.UID;

public class Sub extends Function {
    public Sub() {
        super(new UID("Sub", "SubString"));
    }

    public Sub(Params params) {
        super(new UID("Sub", "SubString"), params, -1);
    }

    @Override
    public String process(String str) {
        if (getParams() == null) return str;
        getParams().setStr(str);
        int start = getParams().asInt(0);
        int end = getParams().asInt(1);
        if (start == -1)
            return str;
        if (end == -1) {
            if (start >= str.length() || start <= 0) {
                return str;
            }
            return str.substring(start);
        }
        if (start > end || start >= str.length()
                || start < 0 || end > str.length()) {
            return str;
        }
        return str.substring(start, end);
    }
}
