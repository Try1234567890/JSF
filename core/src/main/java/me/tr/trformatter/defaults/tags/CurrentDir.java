package me.tr.trformatter.defaults.tags;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

public class CurrentDir extends Tag {
    public CurrentDir() {
        super(new UID("current_dir"));
    }

    @Override
    public String evaluate(ParamsContainer params) {
        return System.getProperty("user.dir");
    }
}
