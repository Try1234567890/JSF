package me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

public interface ValueGetter<T> {

    T get(ParamsContainer params, String name);

    default T get(ParamsContainer params, UID name) {
        T result = get(params, name.getName());
        if (result != null) {
            return result;
        }
        int i = 0;
        String[] aliases = name.getAliases();
        while (i < aliases.length && (result = get(params, aliases[i])) == null) {
            i++;
        }
        return result;
    }

}
