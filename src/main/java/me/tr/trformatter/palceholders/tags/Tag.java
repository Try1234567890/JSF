package me.tr.trformatter.palceholders.tags;

import me.tr.trformatter.TrFormatter;
import me.tr.trformatter.uids.UID;

public abstract class Tag {
    private String str;
    private UID uniqueID;
    private String replace;

    public Tag(UID uniqueID, String replace) {
        this.uniqueID = uniqueID;
        this.replace = replace;
    }

    protected Tag(UID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public UID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String resolve(String str, String replace) {
        str = str.replace(formatID(getUniqueID().getId()), replace);
        for (String id : getUniqueID().getAliases()) {
            str = str.replace(formatID(id), replace);
        }
        return str;
    }

    private String formatID(String id) {
        return TrFormatter.START_TAG.toString() + id + TrFormatter.END_TAG.toString();
    }

    @Override
    public String toString() {
        return "Tag{uniqueID=" + uniqueID + ", replace='" + replace + '\'' + '}';
    }
}
