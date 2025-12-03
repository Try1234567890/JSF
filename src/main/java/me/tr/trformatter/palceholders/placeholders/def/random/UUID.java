package me.tr.trformatter.palceholders.placeholders.def.random;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

public class UUID extends Placeholder {
    public UUID() {
        super(new UID("UUID"));
    }

    public UUID(Params params, Function[] functions, int start, int end) {
        super(new UID("UUID"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public UUID newInstance(Params params, Function[] functions, int start, int end) {
        return new UUID(params, functions, start, end);
    }
}
