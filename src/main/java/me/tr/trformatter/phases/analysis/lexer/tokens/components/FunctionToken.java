package me.tr.trformatter.phases.analysis.lexer.tokens.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.NameToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.Token;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.ParamToken;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.Parameterized;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsMapper;

import java.util.List;

public class FunctionToken implements Token, Parameterized {
    private final NameToken name;
    private final List<ParamToken> params;
    private final ParamsContainer paramsContainer;

    public FunctionToken(NameToken name, List<ParamToken> params) {
        this.name = name;
        this.params = params;
        this.paramsContainer = ParamsMapper.map(params);
    }

    public NameToken getName() {
        return name;
    }

    public List<ParamToken> getParams() {
        return params;
    }

    @Override
    public ParamsContainer params() {
        return paramsContainer;
    }

    public boolean hasParams() {
        return params != null && !params.isEmpty();
    }

    @Override
    public String asString() {
        return "%[" + name.asString() + (hasParams() ? "(" + String.join(",", getParams().stream().map(ParamToken::asString).toList()) + ")" : "") + "]";
    }

    @Override
    public String toString() {
        return asString();
    }
}
