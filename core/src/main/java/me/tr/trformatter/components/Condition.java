package me.tr.trformatter.components;

import me.tr.trformatter.defaults.conditions.Comparator;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.registries.ConditionsRegistry;
import me.tr.trformatter.uids.UID;
import me.tr.utilities.validators.Preconditions;

import java.util.Optional;

public abstract class Condition implements Component {
    private final UID uid;

    public Condition(UID uid) {
        this.uid = Preconditions.parameterNotNull(uid, "uid");
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

    public abstract Comparator getComparator(ParamsContainer params);

    public static Optional<Condition> getCondition(UID name) {
        return ConditionsRegistry.getInstance().getOptional(name);
    }

    public static Optional<Condition> getCondition(String name) {
        return ConditionsRegistry.getInstance().retrieve(name);
    }

}
