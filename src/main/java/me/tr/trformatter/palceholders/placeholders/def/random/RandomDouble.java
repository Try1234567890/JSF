package me.tr.trformatter.palceholders.placeholders.def.random;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

public class RandomDouble extends Placeholder {
    public RandomDouble() {
        super(new UID("RandomDouble"));
    }

    public RandomDouble(Params params, Function[] functions, int start, int end) {
        super(new UID("RandomDouble"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        double min = getParams().asDouble(0);
        double max = getParams().asDouble(1);
        if (min < 0) {
            min = 0;
        }
        if (max < 0) {
            min = Double.MAX_VALUE;
        }
        if (min > max) {
            return String.valueOf(new java.util.Random().nextDouble(0, Double.MAX_VALUE));
        }
        return String.valueOf(new java.util.Random().nextDouble(min, max));
    }
}
