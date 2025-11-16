package me.tr.formatter.palceholders.placeholders.def.random;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.uids.UID;

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
}
