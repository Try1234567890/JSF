package me.tr.trformatter.registries;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.defaults.tags.CurrentDir;
import me.tr.trformatter.defaults.tags.SystemEnv;
import me.tr.trformatter.defaults.tags.UserHome;
import me.tr.trformatter.defaults.tags.date.Now;
import me.tr.trformatter.defaults.tags.readfile.ReadFile;
import me.tr.trformatter.defaults.tags.sendmessage.SendMessage;
import me.tr.trformatter.uids.DuplicateUIDException;
import me.tr.trformatter.uids.UID;

import java.util.Arrays;
import java.util.Map;

public class TagsRegistry extends Registry<UID, Tag> {
    private static TagsRegistry instance;

    private TagsRegistry() {
        register(new SendMessage());
        register(new SystemEnv());
        register(new ReadFile());
        register(new CurrentDir());
        register(new UserHome());
        register(new Now());
    }

    public static TagsRegistry getInstance() {
        if (instance == null) {
            instance = new TagsRegistry();
        }
        return instance;
    }

    @Override
    public void register(UID key, Tag value) {
        if (has(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the tag UID is unique. Suggestion: prefix it with your app name (e.g., \"TrFormatter-OsName\").");
        }
        super.register(key, value);
    }

    public void register(Tag value) {
        UID key = value.getUID();
        if (has(key)) {
            throw new DuplicateUIDException("Duplicate UID detected for: " + value + ". Please ensure the tag UID is unique. Suggestion: prefix it with your app name (e.g., \"TrFormatter-IsLinux\").");
        }
        super.register(key, value);
    }

    public Tag retrieve(String key) {
        for (Map.Entry<UID, Tag> entry : getRegistry().entrySet()) {
            UID uid = entry.getKey();
            if (uid.getName().equals(key)
                    || Arrays.asList(uid.getAliases()).contains(key)) {
                return entry.getValue();
            }
        }

        return null;
    }
}
