package me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;

import java.io.File;
import java.util.Optional;

public class StringGetter implements ValueGetter<String> {
    @Override
    public String get(ParamsContainer params, String name) {
        Object obj = params.get(name);

        return String.valueOf(obj);
    }
}
