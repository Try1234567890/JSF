package me.tr.trformatter.defaults.functions.cases;

import me.tr.trformatter.uids.UID;

public class ToLowerCase extends ToCase {

    public ToLowerCase() {
        super(new UID("to_lower_case"));
    }

    @Override
    protected String toCase(String original) {
        return original.toLowerCase();
    }
}
