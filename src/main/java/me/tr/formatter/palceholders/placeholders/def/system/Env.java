package me.tr.formatter.palceholders.placeholders.def.system;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.uids.UID;

public class Env extends Placeholder {

    public Env() {
        super(new UID("Env"));
    }

    public Env(Params params, Function[] functions, int start, int end) {
        super(new UID("Env"), params, functions, start, end, 1);
    }

    @Override
    public String process(String str) {
        return System.getenv(str);
    }
}
