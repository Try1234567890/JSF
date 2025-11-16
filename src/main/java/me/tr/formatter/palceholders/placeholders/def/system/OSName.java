package me.tr.formatter.palceholders.placeholders.def.system;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.uids.UID;

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
