package me.tr.trformatter.defaults.tags.system_env;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Tag;
import me.tr.trformatter.uids.UID;

public class SystemEnv extends Tag {

    public SystemEnv() {
        super(new UID("system_env"));
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return the string to replace with placeholder in raw text.
     */
    @Override
    public String evaluate(ParamsContainer params) {
        return System.getProperty(getKey(params));
    }

    private String getKey(ParamsContainer params) {
        String key = params.getAs("key", String.class);

        if (key == null) {
            throw new IllegalArgumentException("\"key\" param in " + params + " is missing.");
        }

        return key;
    }
}
