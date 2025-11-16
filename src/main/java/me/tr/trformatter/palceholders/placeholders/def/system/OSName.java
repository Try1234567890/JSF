package me.tr.trformatter.palceholders.placeholders.def.system;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

public class OSName extends Placeholder {
    public OSName() {
        super(new UID("OSName"));
    }

    public OSName(Params params, Function[] functions, int start, int end, int requiredParams) {
        super(new UID("OSName"), params, functions, start, end, requiredParams);
    }

    @Override
    public String process(String str) {
        return new Property(new Params(str, new Object[]{"os.name"}), getFunctions(), getStart(), getEnd()).process(str);
    }
}
