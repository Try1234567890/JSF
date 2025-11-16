package me.tr.formatter.palceholders.placeholders.def.file;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.palceholders.placeholders.def.system.Property;
import me.tr.formatter.uids.UID;

public class CurrentPath extends Placeholder {

    private CurrentPath() {
        super(new UID("CurrentPath", "Path"));
    }

    public CurrentPath(Params params, Function[] functions, int start, int end) {
        super(new UID("CurrentPath", "Path"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        return new Property(new Params(str, new Object[]{"user.dir"}), getFunctions(), getStart(), getEnd()).process(str);
    }
}
