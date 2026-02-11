package me.tr.trformatter.defaults.tags;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;
import me.tr.trformatter.utility.Validator;

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
        String key = params.getAsOrNull("key", String.class);

        Validator.isNull("\"key\" param is not specified in " + params);

        return key;
    }
}
