package me.tr.trformatter.components;

import me.tr.trformatter.Validator;
import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.registries.ConditionsRegistry;
import me.tr.trformatter.uids.UID;

import java.util.Optional;

public abstract class Condition implements Component {
    private final UID uid;

    public Condition(UID uid) {
        Validator.isNull(uid, "The identifier cannot be null");
        this.uid = uid;
    }

    @Override
    public UID getUID() {
        return uid;
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return {@code true} if the placeholder ends successfully (so the process continue), otherwise {@code false}.
     */
    public abstract boolean evaluate(ParamsContainer params);

    public static Optional<Condition> getCondition(UID name) {
        return Optional.ofNullable(ConditionsRegistry.getInstance().retrieve(name));
    }

    public static Optional<Condition> getCondition(String name) {
        return Optional.ofNullable(ConditionsRegistry.getInstance().retrieve(name));
    }

}
