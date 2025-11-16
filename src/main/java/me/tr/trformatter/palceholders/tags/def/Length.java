package me.tr.trformatter.palceholders.tags.def;

import me.tr.trformatter.palceholders.tags.Tag;
import me.tr.trformatter.uids.UID;

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
