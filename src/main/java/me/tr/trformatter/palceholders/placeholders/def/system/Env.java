package me.tr.trformatter.palceholders.placeholders.def.system;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

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

    @Override
    public Env newInstance(Params params, Function[] functions, int start, int end) {
        return new Env(params, functions, start, end);
    }
}
