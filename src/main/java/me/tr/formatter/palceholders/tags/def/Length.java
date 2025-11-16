package me.tr.formatter.palceholders.tags.def;

import me.tr.formatter.palceholders.tags.Tag;
import me.tr.formatter.uids.UID;

public class Length extends Tag {
    public Length(String replace) {
        super(new UID("Length"), replace);
    }

    protected Length() {
        super(new UID("Length"));
    }


    @Override
    public void setStr(String str) {
        super.setStr(str);
        setReplace(String.valueOf(str.length()));
    }
}
