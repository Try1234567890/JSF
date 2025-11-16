package me.tr.formatter.palceholders.placeholders.def.random;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.uids.UID;

public class RandomInt extends Placeholder {


    public RandomInt() {
        super(new UID("RandomInt"));
    }

    public RandomInt(Params params, Function[] functions, int start, int end) {
        super(new UID("RandomInt"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        return new RandomDouble(getParams(), getFunctions(), getStart(), getEnd()).process(str);
    }
}
