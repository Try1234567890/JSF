package me.tr.trformatter.analysis.lexer.tokens.params.manager;

import me.tr.trformatter.analysis.lexer.tokens.params.ParamToken;

import java.util.List;

public class ParamsMapper {

    private ParamsMapper() {

    }

    public static ParamsContainer map(List<ParamToken> tokens) {
        ParamsContainer params = new ParamsContainer();

        if (tokens == null || tokens.isEmpty()) {
            return params;
        }

        for (ParamToken token : tokens) {
            if (token == null) continue;
            String name = token.getName();
            Object val = token.getValue();
            params.insert(name, val);
        }

        return params;
    }

}
