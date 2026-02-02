package me.tr.trformatter.defaults.functions.cases;

import me.tr.trformatter.uids.UID;

public class ToUpperCase extends ToCase {

    public ToUpperCase() {
        super(new UID("to_upper_case"));
    }

    @Override
    protected String toCase(String original) {
        return original.toUpperCase();
    }
}
