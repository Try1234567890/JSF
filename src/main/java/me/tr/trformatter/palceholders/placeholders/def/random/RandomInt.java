package me.tr.trformatter.palceholders.placeholders.def.random;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

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

    @Override
    public RandomInt newInstance(Params params, Function[] functions, int start, int end) {
        return new RandomInt(params, functions, start, end);
    }
}
