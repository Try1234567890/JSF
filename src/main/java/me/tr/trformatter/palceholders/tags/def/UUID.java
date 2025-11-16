package me.tr.trformatter.palceholders.tags.def;

import me.tr.trformatter.palceholders.tags.Tag;
import me.tr.trformatter.uids.UID;

public class UUID extends Tag {

    protected UUID() {
        super(new UID("UUID"), java.util.UUID.randomUUID().toString());
    }
}
