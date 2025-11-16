package me.tr.trformatter.palceholders.placeholders.def.file;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.palceholders.placeholders.def.system.Property;
import me.tr.trformatter.uids.UID;

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
