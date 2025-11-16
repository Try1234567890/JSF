package me.tr.formatter.palceholders.tags.def;

import me.tr.formatter.palceholders.tags.Tag;
import me.tr.formatter.uids.UID;

public class UUID extends Tag {

    protected UUID() {
        super(new UID("UUID"), java.util.UUID.randomUUID().toString());
    }
}
