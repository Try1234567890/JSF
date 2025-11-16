package me.tr.trformatter.palceholders.placeholders.def.system;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

public class Property extends Placeholder {

    public Property() {
        super(new UID("Property"));
    }

    public Property(Params params, Function[] functions, int start, int end) {
        super(new UID("Property"), params, functions, start, end, 1);
    }

    @Override
    public String process(String str) {
        String property = getParams().asString(0);
        if (property.isEmpty()) {
            return "PropertyIsNotSpecified";
        }
        return System.getProperty(property);
    }
}
